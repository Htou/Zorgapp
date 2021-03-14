package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JasonWriter {

    public void jasonWriter(String voornaam, String achternaam, int leeftijd) throws IOException {

        Profile profile = new Profile();
        profile.SetVoornaam("Hichem");
        profile.SetAchternaam("Touati");
        profile.SetLeeftijd(27);

        Writer writer = new FileWriter("/home/hichem/Dropbox/HU-ADSD/S1/ZorgApp/src/test/testfiles/test.json");
        Gson gson = new Gson();
        // 1. Java object to JSON file
        gson.toJson(profile, writer);
        writer.flush(); //flush data to file   <---
        writer.close(); //close write
    }

    public void jasonWriterVoornaam(String voornaam) throws IOException {

        Profile profile = new Profile();
        // Laad json
        //schrijf data van json naar profile
        //pas voornaam aan in profile.
        //converteer profile naar json
    }

    public void jasonWriterAchternaam(String achternaam) throws IOException {

        Profile profile = new Profile();
        // Laad json
        //schrijf data van json naar profile
        //pas voornaam aan in profile.
        //converteer profile naar json
    }

    public void jasonWriterLeeftijd(int leeftijd) throws IOException {

        Profile profile = new Profile();
        // Laad json
        //schrijf data van json naar profile
        //pas voornaam aan in profile.
        //converteer profile naar json
    }


    public void jasonWriterGewicht(double gewicht) throws IOException {

        Profile profile = new Profile();
        // Laad json
        //schrijf data van json naar profile
        //pas voornaam aan in profile.
        //converteer profile naar json
    }

    public void jasonWriterLengte(double lengte) throws IOException {
        Profile profile = new Profile();
        // Laad json
        //schrijf data van json naar profile
        //pas voornaam aan in profile.
        //converteer profile naar json
    }

    public void SaveProfileList(ProfileList profeList) throws IOException {
        Writer writer = new FileWriter("/home/hichem/Dropbox/HU-ADSD/S1/ZorgApp/src/test/testfiles/test.json");
        Gson gson = new Gson();
        // 1. Java object to JSON file
        gson.toJson(profeList, writer);
        writer.flush(); //flush data to file   <---
        writer.close(); //close write
    }
}
