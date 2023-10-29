package org.example;

import java.util.List;
import java.util.Scanner;

public class Jatek {
    private Hos hos;
    private Labirintus labirintus;

    public Jatek(Hos hos, Labirintus labirintus) {
        this.hos = hos;
        this.labirintus = labirintus;
        if (labirintus.getElemek().isEmpty()){
            System.out.println("elobb olvas be egy labirintust anelkul nem tudunk jatszani");
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n1. lép ");
        System.out.println("2. jobbra fordul ");
        System.out.println("3. balra fordul");
        System.out.println("4. lő");
        System.out.println("5. feladás ");
        System.out.println("Válasszon a következő menüpontok közül:");
        int choice = scanner.nextInt();
        switch (choice) {
            // lep
            case 1:
                lep();
//                if(win()) {}
//                if(gameover()) {}
//                if(veremreLep()) {}
                labirintus.rajzoljukKi(hos.getPozicio());
                play();
                break;
            case 2:
                jobbra();
                play();
                break;
            case 3:
                balra();
                play();
                break;
            case 4:
                lo();
                play();
                break;
            case 5:
                break;
            default:
                System.out.println("Ezt a szamot nem ismerem valasz masikat");
                play();
                break;

        }

    }


    public void jobbra() {
        switch (hos.getIrany()) {
            case N:
                hos.setIrany(Irany.E);
                break;
            case E:
                hos.setIrany(Irany.S);
                break;
            case S:
                hos.setIrany(Irany.W);
                break;
            case W:
                hos.setIrany(Irany.N);
                break;
        }
    }
    public void balra() {
        switch (hos.getIrany()) {
            case N:
                hos.setIrany(Irany.W);
                break;
            case W:
                hos.setIrany(Irany.S);
                break;
            case S:
                hos.setIrany(Irany.E);
                break;
            case E:
                hos.setIrany(Irany.N);
                break;
        }
    }

    private boolean joHelyreAkarunkLepni(int hova) {
        // hova lehet +1, -1, +N, -N
        int aktualHosPozicio = hos.getPozicio();
        int ujPozicioLenne = aktualHosPozicio + hova;

        if (ujPozicioLenne < 0) {
            return false;
        }
        if (ujPozicioLenne >= labirintus.getElemek().size()) {
            return false;
        }
        if (labirintus.getElemek().get(ujPozicioLenne).equals("W")) {
            return false;
        }
        return true;
    }
    private void lep() {
        int aktualHosPozicio = hos.getPozicio();
        switch (hos.getIrany()) {
            case E:
                if (joHelyreAkarunkLepni(1)) {
                    veremreLep(1);
                    hos.setPozicio(aktualHosPozicio + 1);
                } else {
                    System.out.println("IDE nem lephetsz ");
                }
                break;
            case W:
                if (joHelyreAkarunkLepni(-1)) {
                    veremreLep(-1);
                    hos.setPozicio(aktualHosPozicio - 1);
                } else {
                    System.out.println("IDE nem lephetsz ");
                }
                break;
            case N:
                if (joHelyreAkarunkLepni(-labirintus.getMeretN())) {
                    veremreLep(-labirintus.getMeretN());
                    hos.setPozicio(aktualHosPozicio - labirintus.getMeretN());
                } else {
                    System.out.println("IDE nem lephetsz ");
                }
                break;
            case S:
                if (joHelyreAkarunkLepni(labirintus.getMeretN())) {
                    veremreLep(labirintus.getMeretN());
                    hos.setPozicio(aktualHosPozicio + labirintus.getMeretN());
                } else {
                    System.out.println("IDE nem lephetsz ");
                }
                break;
        }
    }

    private void veremreLep(int hova) {
        int aktualHosPozicio = hos.getPozicio();
        int ujPozicioLesz = aktualHosPozicio + hova;
        if ( labirintus.getElemek().get(ujPozicioLesz).equals("P") ) {
            hos.setNyilak( hos.getNyilak() - 1);
        }
    }

    private boolean win () {
        if (hos.isNalamVanAzArany()) {
            if (hos.getPozicio() == labirintus.getHosStart()) {
                System.out.println("ÖN nyert");
                System.out.println("elmentjuk az eredmeny");
                // TODO eredmenyelmetese
                return true;
            }
        }
        return false;
    }

    private boolean gameover() {
        if (labirintus.getWumpusokHelye().contains(hos.getPozicio())){
            System.out.println("gameover");
            // TODO lepjunk ki
            return true;
        }
        return false;
    }

    private void lo() {
        hos.setNyilak( hos.getNyilak() -1 );
        int nyilActPoz = hos.getPozicio();
        switch (hos.getIrany()) {
            case E:
                nyilActPoz = nyilActPoz + 1;
                keletreLo(nyilActPoz);
                break;
            case W:
                nyilActPoz = nyilActPoz - 1;
                nyugatraLo(nyilActPoz);
                break;
            case N:
                nyilActPoz = nyilActPoz - 4;
                eszakraLo(nyilActPoz);
                break;
            case S:
                nyilActPoz = nyilActPoz + 4;
                delreLo(nyilActPoz);
                break;

        }
    }

    private boolean keletreLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz <= labirintus.getMeretN()) {
            if (labirintus.getElemek().get(nyilActPoz) != "W") {
                if (labirintus.getElemek().get(nyilActPoz) == "U") {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove(nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    // TODO a nyilvesszo sikeres volt
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    keletreLo(nyilActPoz + 1 );
                }
            }
        }
        return talalt;
    }

    private boolean nyugatraLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz > 0) {
            if (labirintus.getElemek().get(nyilActPoz) != "W") {
                if (labirintus.getElemek().get(nyilActPoz) == "U") {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove(nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    // TODO a nyilvesszo sikeres volt
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    nyugatraLo(nyilActPoz - 1 );
                }
            }
        }
        return talalt;
    }


    private boolean eszakraLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz >= 0) {
            if (labirintus.getElemek().get(nyilActPoz) != "W") {
                if (labirintus.getElemek().get(nyilActPoz) == "U") {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove(nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    // TODO a nyilvesszo sikeres volt
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    eszakraLo(nyilActPoz - 4 );
                }
            }
        }
        return talalt;
    }

    private boolean delreLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz < labirintus.getElemek().size()) {
            if (labirintus.getElemek().get(nyilActPoz) != "W") {
                if (labirintus.getElemek().get(nyilActPoz) == "U") {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove(nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    // TODO a nyilvesszo sikeres volt
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    nyugatraLo(nyilActPoz + 4 );
                }
            }
        }
        return talalt;
    }


}