package com.company;

import java.util.ArrayList;

public class MedicineList {
    ArrayList<Medicine> medicijn;

    public MedicineList() {
        medicijn = new ArrayList<Medicine>();
    }

    public void add(Medicine medicine) {
        medicijn.add(medicine);
    }

    public Medicine get(int index) {
        return medicijn.get(index);
    }

    public void remove(int index) {
        medicijn.remove(index);
    }

    public int sizeOf() {
        return medicijn.size();
    }
}