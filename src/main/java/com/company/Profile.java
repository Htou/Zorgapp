package com.company;

import java.util.ArrayList;

public class Profile {
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private double gewicht;
    private WeightList gewichtenlijst;
    private double lengte;
    private MedicineList medicijnlijst;

    public Profile() {
        voornaam = "";
        achternaam = "";
        gewichtenlijst = new WeightList();
        medicijnlijst = new MedicineList();
    }

//    public Profile(String voornaam, String achternaam) {
//        this.voornaam = voornaam;
//        this.achternaam = achternaam;
//    }
//
//    public Profile(String voornaam, String achternaam, int leeftijd, double gewicht, double lengte) {
//        this.voornaam = voornaam;
//        this.achternaam = achternaam;
//        this.leeftijd = leeftijd;
//        this.gewicht = gewicht;
//        this.lengte = lengte;
//    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public double getLengte() {
        return lengte;
    }

    public void setLengte(double lengte) {
        this.lengte = lengte;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public WeightList getGewichtenlijst() {
        return gewichtenlijst;
    }

    public MedicineList getMedicijnlijst() {
        return medicijnlijst;
    }

    public String getBmi() {
        double bmi;
        bmi = (getGewicht() / (Math.pow(getLengte(), 2)));
        return String.format("%.1f", bmi);
    }
}