package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gyozelmek {
    private static List<String> jatekosnev = new ArrayList<>();
    private static List<Integer> gyozelemszam = new ArrayList<>();
    public static void adatMentes(FelhaszNev felhaszNev) {
        Gyozelmek.adatOlvasas();
        int i = 0;
        boolean talalat = false;
        for (String jatekos : jatekosnev) {
            if (jatekos.equals(felhaszNev.getFelhasznaloNev())) {
                gyozelemszam.set(i, gyozelemszam.get(i) + 1);
                talalat = true;
            }
            i++;
        }
        if (!talalat) {
            jatekosnev.add(felhaszNev.getFelhasznaloNev());
            gyozelemszam.add(1);
        }
        try {

            FileWriter writer = new FileWriter("eredmenyek.txt");
            for (int j = 0; j < jatekosnev.size(); j++) {
                String sor = jatekosnev.get(j) + ":" + gyozelemszam.get(j);
                writer.write(sor + "\n");
            }
            writer.close();
            System.out.println("Eredmény elmentve a 'eredmenyek.txt' fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba az eredmény mentése közben: " + e.getMessage());
        }
    }
    public static void adatOlvasas() {
        try {
            FileReader reader = new FileReader("eredmenyek.txt");
            BufferedReader br = new BufferedReader(reader);
            String fajltartalma;
            while ((fajltartalma = br.readLine()) != null) {
                String[] n = fajltartalma.split(":");
                jatekosnev.add(n[0]);
                gyozelemszam.add(Integer.valueOf(n[1]));
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Hiba az eredmény mentése közben: " + e.getMessage());
        }
    }
}
