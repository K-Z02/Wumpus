package org.example;

public class Hos {
    private int pozicio;
    private Irany irany;
    private int nyilak;

    private boolean nalamVanAzArany;

    public Hos() {
    }
    public Hos(int pozicio, Irany irany, int nyilak) {
        this.pozicio = pozicio;
        this.irany = irany;
        this.nyilak = nyilak;
        this.nalamVanAzArany = false;
    }

    public Hos(int pozicio, Irany irany, int nyilak, boolean nalamVanAzArany) {
        this.pozicio = pozicio;
        this.irany = irany;
        this.nyilak = nyilak;
        this.nalamVanAzArany = nalamVanAzArany;
    }

    public int getPozicio() {
        return pozicio;
    }

    public void setPozicio(int pozicio) {
        this.pozicio = pozicio;
    }

    public Irany getIrany() {
        return irany;
    }

    public void setIrany(Irany irany) {
        this.irany = irany;
    }

    public int getNyilak() {
        return nyilak;
    }

    public void setNyilak(int nyilak) {
        this.nyilak = nyilak;
    }

    public boolean isNalamVanAzArany() {
        return nalamVanAzArany;
    }

    public void setNalamVanAzArany(boolean nalamVanAzArany) {
        this.nalamVanAzArany = nalamVanAzArany;
    }

    @Override
    public String toString() {
        return "{" +
                "pozicio: " + pozicio +
                ", irany: " + irany +
                ", nyilak: " + nyilak +
                ", nalamVanAzArany: " + nalamVanAzArany +
                '}';
    }

}