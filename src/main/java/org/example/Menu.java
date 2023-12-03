package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Main
public class Menu {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        Labirintus labirintus = new Labirintus();
        Hos hos = new Hos();
        FelhaszNev felhaszNev = new FelhaszNev(scanner);


        while (run) {
            System.out.println("\nMENÜ:");
            System.out.println("1. Játék indítása");
            System.out.println("2. Térkép beolvasás");
            System.out.println("3. Adatbázisból betöltés");
            System.out.println("4. Adatbázisba mentés");
            System.out.println("5. Kilépés");
            System.out.println("Válasszon a következő menüpontok közül:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        Jatek jatek = new Jatek(hos, labirintus, felhaszNev);
                        jatek.play();

                    }
                    catch (RuntimeException e){
                        break;
                    }
                    break;
                case 2:
                    File file = new File("C:/Users/krist/IdeaProjects/progtech_wumpusz/src/main/resources/wumpuszinput.txt");

                    labirintus.palyaBeolvasas(file);
                    hos = new Hos(labirintus.getHosStart(), labirintus.getIrany(), labirintus.getWumpusokSzama());
                    break;
                case 3:
//                    AdatMentes.olvaso();

                    break;
                case 4:
//                    System.out.println("Adja meg a játék mentés nevét: ");
                    String x = scanner.nextLine();
                    JatekMentes jatekMentes = new JatekMentes(hos,labirintus,x);
                    List<JatekMentes> regiJatekMentes = AdatMentes.olvaso().isPresent() ? AdatMentes.olvaso().get() : new ArrayList<>();

                    regiJatekMentes.add(jatekMentes);
                    AdatMentes.iro(regiJatekMentes);
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Érvénytelen választás. Kérem, válasszon újra.");
            }
        }

        scanner.close();
    }
}