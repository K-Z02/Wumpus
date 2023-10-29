package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Labirintus {
    private List<String> elemek = new ArrayList<>();

    private int meretN;
    private int aranyPozitcioja;
    private int wumpusokSzama=0;

    private List<Integer> wumpusokHelye = new ArrayList<>();
    private List<Integer> vermekHelyzetete = new ArrayList<>();

    public List<String> getElemek() {
        return elemek;
    }

    public int getMeretN() {
        return meretN;
    }

    public int getAranyPozitcioja() {
        return aranyPozitcioja;
    }

    public int getWumpusokSzama() {
        return wumpusokSzama;
    }

    public List<Integer> getWumpusokHelye() {
        return wumpusokHelye;
    }

    public void setWumpusokHelye(List<Integer> wumpusokHelye) {
        this.wumpusokHelye = wumpusokHelye;
    }


    public List<Integer> getVermekHelyzetete() {
        return vermekHelyzetete;
    }

    public int getHosStart() {
        return hosStart;
    }

    public Irany getIrany() {
        return irany;
    }

    private int hosStart;

    private Irany irany;


    public Labirintus() {
    }


    public void palyaBeolvasas(File file) {
        try {
            // Fájl beolvasása
            Scanner scanner = new Scanner(file);

            //Az első sor tartalma
            this.meretN = scanner.nextInt();

            char oszlopPozi = scanner.next().charAt(0);
            int sor = scanner.nextInt();
            this.hosStart = ( ((int) oszlopPozi) - ((int) 'A') )  + (sor - 1) * meretN;
            if (hosStart > (meretN * meretN)) {
                System.out.println("BAJ van, a hos palyan kivül");
            }
            this.irany = Irany.valueOf(String.valueOf(scanner.next().charAt(0)));


            System.out.println("   "+ meretN +" "+ oszlopPozi +" "+ sor +" "+ irany);

            //A pálya többi része
            for (int i = 0; i < meretN; i++) {
                String sorAdat = scanner.next();
                System.out.println((i + 1) + "  " + sorAdat);
                String[] sorElemek = sorAdat.split("");
                for (int j = 0; j < meretN; j++) {
                    this.elemek.add(sorElemek[j]);
                    switch (sorElemek[j].toUpperCase()){
                        case "G":
                            this.aranyPozitcioja = i  * meretN + j;
                            break;
                        case "P":
                            this.vermekHelyzetete.add(i  * meretN + j);
                            break;
                        case "U":
                            this.wumpusokHelye.add(i * meretN + j);
                            this.wumpusokSzama += 1;
                            break;
                        case "_":
                        case "W":
                            break;
                        default:
                            System.out.println("Ezt a betut nem ismerem. Ez egy elrontott terkep. ");
                            break;
                    }
                }
            }

            //Scanner bezárása
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Hiba: A fájl nem található.");
//            throw e;
        }
    }

    public void rajzoljukKi(int hosPozitcioja) {
        StringBuilder sor = new StringBuilder();
        for (int i = 0; i < elemek.size(); i++) {
            if ( hosPozitcioja == i ) {
                sor.append("H");
                continue;
            }
            sor.append(elemek.get(i));
            if ((i % meretN) == (meretN - 1)) {
                System.out.println(sor);
                sor = sor.delete(0,meretN);
            }
        }
    }


    public void palyaSzerkeszto() {
        System.out.println("Nincs implementalva meg, kerem valasszon mast");
    }

    public void mentes(){
        System.out.println("kiirjuk a filet");
    }
}