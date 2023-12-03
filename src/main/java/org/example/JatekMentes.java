package org.example;

public class JatekMentes {
    private Hos hos;
    private Labirintus labirintus;
    private String mentettJatekNev;

    public JatekMentes(Hos hos, Labirintus labirintus, String mentettJatekNev) {
        this.hos = hos;
        this.labirintus = labirintus;
        this.mentettJatekNev = mentettJatekNev;
    }

    public Hos getHos() {
        return hos;
    }

    public void setHos(Hos hos) {
        this.hos = hos;
    }

    public Labirintus getLabirintus() {
        return labirintus;
    }

    public void setLabirintus(Labirintus labirintus) {
        this.labirintus = labirintus;
    }

    public String getMentettJatekNev() {
        return mentettJatekNev;
    }

    public void setMentettJatekNev(String mentettJatekNev) {
        this.mentettJatekNev = mentettJatekNev;
    }

    @Override
    public String toString() {
        return "{" +
                "hos: " + hos +
                ", labirintus: " + labirintus +
                ", jatekmentes: '" + mentettJatekNev + '\'' +
                '}';
    }
}
