package com.company;

import java.util.ArrayList;

public class ProfileList {
   private ArrayList<Profile> collection;

    public ProfileList() {
        collection = new ArrayList<Profile>();
    }

    public void add(Profile profile) {
        collection.add(profile);
    }

    public Profile get(int index) {
        return collection.get(index);
    }

    public void remove(int index) {
        collection.remove(index);
    }

    public int sizeOf() {
        return collection.size();
    }
}