package com.company;

public class Medicine {
    private String naam;
    private int mg;
    private String beschrijving;

    public Medicine() {
        naam = "";
        beschrijving = "";
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getMg() {
        return mg;
    }

    public void setMg(int mg) {
        this.mg = mg;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving() {
        this.beschrijving = beschrijving;
    }
}
