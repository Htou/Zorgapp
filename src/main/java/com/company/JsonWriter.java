package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonWriter {

    public static void saveProfileList(ProfileList profileList) throws IOException {
        Writer writer = new FileWriter("/home/hichem/Dropbox/HU-ADSD/S1/ZorgApp/src/test/testfiles/test.json");
        Gson gson = new Gson();
        // 1. Java object to JSON file
        gson.toJson(profileList, writer);
        writer.flush(); //flush data to file   <---
        writer.close(); //close write
    }
}
