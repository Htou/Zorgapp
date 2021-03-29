package com.company;

import java.time.LocalDate;

public class WeightEntry {
   private double gewicht;
    private LocalDate data;


    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getGewicht() {
        return gewicht;
    }

    public LocalDate getData() {
        return data;
    }
}
