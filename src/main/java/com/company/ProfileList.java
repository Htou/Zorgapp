package com.company;

import java.util.ArrayList;

public class ProfileList {
    ArrayList<Profile> Collection;

    public void Add(Profile profile) {
        Collection.add(profile);
    }

    public Profile Get(int index) {
        return Collection.get(index);
    }

    public void Remove(int index) {
        Collection.remove(index);
    }

    public int SizeOf() {
        return Collection.size();
    }
}