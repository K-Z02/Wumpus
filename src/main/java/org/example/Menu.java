package org.example;

import java.io.File;
import java.util.Scanner;

//Main
public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        Labirintus labirintus = new Labirintus();
        Hos hos = new Hos();
        FelhaszNev felhaszNev = new FelhaszNev(scanner);

        while (run) {
            System.out.println("\n1. Játék indítása");
            System.out.println("2. Pályaszerkesztés");
            System.out.println("3. Fájlból beolvasás");
            System.out.println("4. Adatbázisból betöltés");
            System.out.println("5. Adatbázisba mentés");
            System.out.println("6. Kilépés");
            System.out.println("Válasszon a következő menüpontok közül:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Jatek jatek = new Jatek(hos, labirintus);
                        jatek.play();
                    break;
                case 2:
                    // Pályaszerkesztés: Implementálja a pályaszerkesztési funkciókat.
                    labirintus.palyaSzerkeszto();
                    break;
                case 3:
//                    System.out.println("Hol van a faj?");
//                    File file = new File(scanner.next());
                    File file = new File("C:/Users/krist/IdeaProjects/progtech_wumpusz/src/main/resources/wumpuszinput.txt");

                    labirintus.palyaBeolvasas(file);
                    hos = new Hos(labirintus.getHosStart(), labirintus.getIrany(), labirintus.getWumpusokSzama());
                    break;
                case 4:
                    // Adatbázisból betöltés: Implementálja az adatbázisból való betöltés funkciót.
                    break;
                case 5:
                    // Adatbázisba mentés: Implementálja az adatbázisba való mentés funkciót.
                    break;
                case 6:
                    // Kilépés: Állítsa le a menüt.
                    run = false;
                    break;
                default:
                    System.out.println("Érvénytelen választás. Kérem, válasszon újra.");
            }
        }

        scanner.close();
    }
}