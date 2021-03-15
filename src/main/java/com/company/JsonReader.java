package com.company;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonReader {

    public static ProfileList loadProfileList() throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader("/home/hichem/Dropbox/HU-ADSD/S1/ZorgApp/src/test/testfiles/test.json");

        ProfileList profileList = gson.fromJson(reader,ProfileList.class);

        reader.close();

        return profileList;
    }

}
