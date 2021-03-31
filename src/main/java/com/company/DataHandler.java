package com.company;

import com.google.gson.Gson;

import java.io.*;


public class DataHandler {

    public static ProfileList loadProfileList() throws IOException {

        Gson gson = new Gson();
        Reader reader = new FileReader(ZorgApp.profileDataPath);

        ProfileList profileList = gson.fromJson(reader, ProfileList.class);

        reader.close();
        return profileList;

    }

    public static MedicineList loadMedicineList() throws IOException {

        Gson gson = new Gson();
        Reader reader = new FileReader(ZorgApp.medicineDataPath);

        MedicineList medicineList = gson.fromJson(reader, MedicineList.class);

        reader.close();
        return medicineList;

    }

    public static void saveProfileList(ProfileList profileList) throws IOException {
        Writer writer = new FileWriter(ZorgApp.profileDataPath);
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
//        WeightEntry gewichtEntry = new WeightEntry();
//        gewichtEntry.setGewicht(10.5);
//        gewichtEntry.setData(LocalDate.now());
//        profile.getGewichtenlijst().add(gewichtEntry);
//
//        profileList.add(profile);
//
//        DataHandler.saveProfileList(profileList);
//    }

}
