package org.example;
import java.util.List;
import java.util.Scanner;

public class Jatek {
    private Hos hos;
    private Labirintus labirintus;
    private FelhaszNev fnev;

    public Jatek(Hos hos, Labirintus labirintus, FelhaszNev felhaszNev) {
        this.hos = hos;
        this.labirintus = labirintus;
        this.fnev = felhaszNev;
        if (labirintus.getElemek().isEmpty()) {
            System.out.println("elobb olvas be egy labirintust anelkul nem tudunk jatszani");
        }

    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nJÁTÉK VEZÉRLŐ:");
        System.out.println("1. lép");
        System.out.println("2. jobbra fordul");
        System.out.println("3. balra fordul");
        System.out.println("4. lő");
        System.out.println("5. feladás/visszalépés a menübe");
        System.out.println("Válasszon a következő menüpontok közül:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                lep();
                System.out.println("jelenlegi nyilak száma: " + hos.getNyilak());
                System.out.println("irany: " + hos.getIrany());
                labirintus.rajzoljukKi(hos.getPozicio());
                nalamVanAzArany();
                if (win()) {
                    System.exit(0);
                }
                if (gameover()) {
                    System.exit(0);
                }
                play();
                break;
            case 2:
                System.out.println("jelenlegi nyilak száma: " + hos.getNyilak());
                jobbra();
                labirintus.rajzoljukKi(hos.getPozicio());
                play();
                break;
            case 3:
                System.out.println("jelenlegi nyilak száma: " + hos.getNyilak());
                balra();
                labirintus.rajzoljukKi(hos.getPozicio());
                play();
                break;
            case 4:
                lo();
                System.out.println("jelenlegi nyilak száma: " + hos.getNyilak());
                System.out.println("irany: " + hos.getIrany());
                labirintus.rajzoljukKi(hos.getPozicio());
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
        System.out.println("irany: " + hos.getIrany());
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
        System.out.println("irany: " + hos.getIrany());
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
        if (labirintus.getElemek().get(ujPozicioLesz).equals("P")) {
            hos.setNyilak(hos.getNyilak() - 1);
            System.out.println("verembe lépett");
        }

    }

    private void nalamVanAzArany() {
        if (hos.getPozicio() == labirintus.getAranyPozitcioja()) {
            List<String> x = labirintus.getElemek();
            x.set(hos.getPozicio(), "_");
            labirintus.setElemek(x);
            hos.setNalamVanAzArany(true);
        }
    }

    private boolean win() {
        if (hos.isNalamVanAzArany()) {
            if (hos.getPozicio() == labirintus.getHosStart()) {
                System.out.println("ÖN nyert");
                System.out.println("elmentjuk az eredmeny");
                Gyozelmek.adatMentes(fnev);
                return true;
            }
        }
        return false;
    }

    private boolean gameover() {
        if (labirintus.getWumpusokHelye().contains(hos.getPozicio())) {
            System.out.println("vége a játéknak");
            return true;
        }
        return false;
    }

    private void lo() {
        hos.setNyilak(hos.getNyilak() - 1);
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
                nyilActPoz = nyilActPoz - labirintus.getMeretN();
                eszakraLo(nyilActPoz);
                break;
            case S:
                nyilActPoz = nyilActPoz + labirintus.getMeretN();
                delreLo(nyilActPoz);
                break;

        }
    }

    private boolean keletreLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz <= labirintus.getMeretN()) {
            if (!labirintus.getElemek().get(nyilActPoz).equals("W")) {
                if (labirintus.getElemek().get(nyilActPoz).equals("U")) {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove((Integer) nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    List<String> x = labirintus.getElemek();
                    x.set(nyilActPoz, "_");
                    labirintus.setElemek(x);
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    keletreLo(nyilActPoz + 1);
                }
            }
        }
        return talalt;
    }

    private boolean nyugatraLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz > 0) {
            if (!labirintus.getElemek().get(nyilActPoz).equals("W")) {
                if (labirintus.getElemek().get(nyilActPoz).equals("U")) {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove((Integer) nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    List<String> x = labirintus.getElemek();
                    x.set(nyilActPoz, "_");
                    labirintus.setElemek(x);
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    nyugatraLo(nyilActPoz - 1);
                }
            }
        }
        return talalt;
    }


    private boolean eszakraLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz >= 0) {
            if (!labirintus.getElemek().get(nyilActPoz).equals("W")) {
                if (labirintus.getElemek().get(nyilActPoz).equals("U")) {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove((Integer) nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    List<String> x = labirintus.getElemek();
                    x.set(nyilActPoz, "_");
                    labirintus.setElemek(x);
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    eszakraLo(nyilActPoz - labirintus.getMeretN());
                }
            }
        }
        return talalt;
    }

    private boolean delreLo(int nyilActPoz) {
        boolean talalt = false;
        if (nyilActPoz < labirintus.getElemek().size()) {
            if (!labirintus.getElemek().get(nyilActPoz).equals("W")) {
                if (labirintus.getElemek().get(nyilActPoz).equals("U")) {
                    List<Integer> regiHelye = labirintus.getWumpusokHelye();
                    regiHelye.remove((Integer) nyilActPoz);
                    labirintus.setWumpusokHelye(regiHelye);
                    List<String> x = labirintus.getElemek();
                    x.set(nyilActPoz, "_");
                    labirintus.setElemek(x);
                    System.out.println(" LE lottuk a wumpust ");
                    talalt = true;
                    return talalt;
                } else {
                    nyugatraLo(nyilActPoz + labirintus.getMeretN());
                }
            }
        }
        return talalt;
    }


}