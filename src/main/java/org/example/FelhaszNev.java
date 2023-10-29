package org.example;

import java.util.Scanner;

public class FelhaszNev {
    private String felhasznaloNev;
    public FelhaszNev(Scanner scanner) {
        System.out.print("Kérem adja meg a felhasználó nevét: ");
        this.felhasznaloNev = scanner.nextLine();
        System.out.println("Felhasználó neve: " + felhasznaloNev);
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }
}

