package com.company;

import com.google.gson.Gson;

import java.io.*;


public class DataHandler {

    public static ProfileList loadProfileList() throws IOException {

        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/java/com/company/Data.json");

        ProfileList profileList = gson.fromJson(reader, ProfileList.class);

        reader.close();
        return profileList;

    }

    public static void saveProfileList(ProfileList profileList) throws IOException {
        Writer writer = new FileWriter("src/main/java/com/company/Data.json");
        Gson gson = new Gson();
        // 1. Java object to JSON file
        gson.toJson(profileList, writer);
        writer.flush(); //flush data to file   <---
    }

//    public static void createUserProfile() throws IOException {
//        //ProfileList profileList = DataHandler.loadProfileList();
//        ProfileList profileList = new ProfileList();
//        Profile profile = new Profile();
//        profile.setVoornaam("Hichem");
//        profile.setAchternaam("Touati");
//        profile.setLeeftijd(27);
//        profile.setGewicht(75.0);
//        Medicine med = new Medicine();
//        profile.getMedicijnlijst().add(med);
//        profile.getMedicijnlijst().add(med);
//        profileList.add(profile);
//
//        DataHandler.saveProfileList(profileList);
//    }

}
