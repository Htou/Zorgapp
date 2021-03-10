package com.company;

public class Profile {
    private String Voornaam;
    private String Achternaam;
    private int Leeftijd;
    private double Gewicht;
    private double Lengte;

    public Profile() {

    }

    public Profile(String voornaam, String achternaam) {
        Voornaam = voornaam;
        Achternaam = achternaam;
    }

    public Profile(String voornaam, String achternaam, int leeftijd, double gewicht, double lengte) {
        Voornaam = voornaam;
        Achternaam = achternaam;
        Leeftijd = leeftijd;
        Gewicht = gewicht;
        Lengte = lengte;
    }

    public String GetVoornaam() {
        return Voornaam;
    }

    public void SetVoornaam(String voornaam) {
        Voornaam = voornaam;
    }

    public String GetAchternaam() {
        return Achternaam;
    }

    public void SetAchternaam(String achternaam) {
        Achternaam = achternaam;
    }

    public double GetGewicht() {
        return Gewicht;
    }

    public void SetGewicht(double gewicht) {
        Gewicht = gewicht;
    }

    public double GetLengte() {
        return Lengte;
    }

    public void SetLengte(double lengte) {
        Lengte = lengte;
    }

    public int GetLeeftijd() {
        return Leeftijd;
    }

    public void SetLeeftijd(int leeftijd) {
        Leeftijd = leeftijd;
    }

    public double GetBmi(double gewicht, double lengte) {
        double bmi;
        bmi = (gewicht / (Math.pow(lengte, 2)));
        return bmi;
    }

}