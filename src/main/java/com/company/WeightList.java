package com.company;

import java.util.ArrayList;

public class WeightList {
    private ArrayList<WeightEntry> gewichtenEntry;

    public WeightList() {
        gewichtenEntry = new ArrayList<WeightEntry>();
    }

    public void add(WeightEntry entry) {
        gewichtenEntry.add(entry);
    }

    public WeightEntry get(int index) {
        return gewichtenEntry.get(index);
    }

    public void remove(int index) {
        gewichtenEntry.remove(index);
    }

    public int sizeOf() {
        return gewichtenEntry.size();
    }
}