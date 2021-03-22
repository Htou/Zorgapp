package com.company;

import java.io.IOException;
import java.util.Scanner;


public class ZorgApp {
    ProfileList profileList;
    boolean exit;

    int choice;
    int profileChoice;
    int profileIndex;

    public ZorgApp() throws IOException {
        profileList = DataHandler.loadProfileList();
        exit = false;
        choice = -1;
        profileChoice = -1;
        profileIndex = profileChoice - 1;

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void unknownError() {
        System.out.println("Er trad een onbekende fout op");
    }

    private void exitZorgapp() {
        exit = true;
        System.out.println("Bedankt voor het gebruiken van onze applicatie");
    }

    public void runMainMenu() throws IOException, InterruptedException {
        printHeader();
        while (!exit) {
            printMainMenu();
            choice = getMainMenuInput();
            preformMainMenuAction();
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|            Welkom bij             |");
        System.out.println("|            de Zorgapp             |");
        System.out.println("+-----------------------------------+");
    }

    private void printMainMenu() {
        System.out.println("\nLogin: Kies een gebruiker");
        System.out.println("1) Inloggen Zorgverlener");
        System.out.println("2) Inloggen als Patient");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private int getMainMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer. Kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void preformMainMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> exitZorgapp();
            case 1 -> runZorgverlenerMenu();
            case 2 -> runMainMenu();
            default -> unknownError();
        }

    }

    private void runZorgverlenerMenu() throws IOException, InterruptedException {
        while (!exit) {
            printZorgverlenerMenu();
            choice = getMainMenuInput();
            preformZorgverlenerMenuAction();
        }
    }

    private void printZorgverlenerMenu() {
        clearScreen();
        System.out.println("\nGebruiker: Zorgverlener");
        System.out.println("1) Geef patientenlijst weer");
        System.out.println("2) Ga terug");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private void preformZorgverlenerMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> exitZorgapp();
            case 1 -> runPatientListMenu();
            case 2 -> runMainMenu();
            default -> unknownError();
        }
    }

    private void runPatientListMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientListMenu();
            profileChoice = getPatientListMenuInput();
            preformPatientListMenuAction();
        }
    }

    private void printPatientListMenu() {
        clearScreen();
        profileIndex = profileChoice - 1;
        System.out.println("Patientenlijst:");
        for (int profileIndex = 0; profileIndex < profileList.sizeOf(); profileIndex++) {
            System.out.println((profileIndex + 1) + ")" + " " + profileList.get(profileIndex).getVoornaam() + " " + profileList.get(profileIndex).getAchternaam());
        }
    }

    private int getPatientListMenuInput() throws IOException {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > profileList.sizeOf()) {
            try {
                System.out.println("\nVoer een patientnummer uit de lijst in om de desbetreffende patient weer te geven: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void preformPatientListMenuAction() throws IOException, InterruptedException {
        if (profileChoice >= 0 || profileChoice <= profileList.sizeOf()) {
            runPatientProfileMenu();
        } else {
            System.out.println("Verkeerde invoer, kies een patientnummer uit de lijst");
        }
    }

    private void runPatientProfileMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfile();
            printPatientProfileMenu();
            choice = getPatientProfileMenuInput();
            preformPatientProfileMenuAction(choice);
        }
    }

    private void printPatientProfile() {
        clearScreen();
        int profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);
        MedicineList medicineList = profile.getMedicijnlijst();

        System.out.println("Patientenprofiel:");
        System.out.println("Voornaam:" + " " + profile.getVoornaam());
        System.out.println("Achternaam:" + " " + profile.getAchternaam());
        System.out.println("Leeftijd:" + " " + profile.getLeeftijd());
        System.out.println("Gewicht:" + " " + profile.getGewicht() + "kg");
        System.out.println("lengte:" + " " + profile.getLengte() + "m");
        System.out.println("BMI:" + " " + profile.getBmi());
        System.out.println("\nMedicijnvoorschrift:");

        for (int medicineIndex = 0; medicineIndex < profileList.get(profileIndex).getMedicijnlijst().sizeOf(); medicineIndex++) {
            System.out.println(medicineList.get(medicineIndex).getNaam() + " " + medicineList.get(medicineIndex).getMg() + "mg");
        }
    }

    private void printPatientProfileMenu() {
        clearScreen();

        System.out.println("\n1) Patientgegevens bewerken");
        System.out.println("2) Medicijnvoorschrift bewerken");
        System.out.println("3) Ga terug naar zorgverlenermenu");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private int getPatientProfileMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer. Kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void preformPatientProfileMenuAction(int choice) throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> exitZorgapp();
            case 1 -> runPatientProfileEditorMenu();
            case 2 -> runZorgverlenerMenu();
            default -> unknownError();
        }
    }

    private void runPatientProfileEditorMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfileEditorMenu();
            choice = getPatientProfileEditorMenuInput();
            boolean dataWritten = preformPatientProfileEditorMenuAction();

            if(dataWritten) {
                DataHandler.saveProfileList(profileList);
            }
        }
    }

    private void printPatientProfileEditorMenu() {
        clearScreen();
        int profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);

        System.out.println("Patientenprofiel bewerken:");
        System.out.println("1) Bewerk voornaam:" + " " + profile.getVoornaam());
        System.out.println("2) Bewerk achternaam:" + " " + profile.getAchternaam());
        System.out.println("3) Bewerk leeftijd:" + " " + profile.getLeeftijd());
        System.out.println("4) Bewerk gewicht:" + " " + profile.getGewicht() + "kg");
        System.out.println("5) Bewerk lengte:" + " " + profile.getLengte() + "m");
        System.out.println("****************************************************");
        System.out.println("6) Ga terug naar zorgverlenermenu");
        System.out.println("0) Zorgapp uitschakelen");

    }

    private int getPatientProfileEditorMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 6) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer. Kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private String getStringInput() {
        Scanner kb = new Scanner(System.in);
        String stringInput = "";
        try {
            stringInput = kb.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringInput;
    }
    
    private int getIntegerInput() {
        Scanner kb = new Scanner(System.in);
        int intInput = 0;
        try {
            intInput = Integer.parseInt(kb.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intInput;
    }

    private double getDoubleInput() {
        Scanner kb = new Scanner(System.in);
        double doubleInput = 0.0;
        try {
            doubleInput = Double.parseDouble(kb.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doubleInput;
    }


    private void runSetVoornaam() {
        profileIndex = profileChoice - 1;
        System.out.println("Vul voornaam van patient in:");
        String voornaam = getStringInput();
        profileList.get(profileIndex).setVoornaam(voornaam);
    }

    private void runSetActernaam() {
        System.out.println("Vul achternaam van patient in:");
        profileIndex = profileChoice - 1;
        String achternaam = getStringInput();
        profileList.get(profileIndex).setAchternaam(achternaam);
    }

    private void runSetLeeftijd() {
        System.out.println("Vul leeftijd van patient in:");
        profileIndex = profileChoice - 1;
        int leeftijd = getIntegerInput();
        profileList.get(profileIndex).setLeeftijd(leeftijd);
    }

    private void runSetGewicht() {
        System.out.println("Vul gewicht van patient in kilogram (voorbeeld: 65.50):");
        profileIndex = profileChoice - 1;
        double gewicht = getDoubleInput();
        profileList.get(profileIndex).setGewicht(gewicht);
    }


    private void runSetLengte() {
        System.out.println("Vul lengte van patient in kilogram (voorbeeld: 65.50):");
        profileIndex = profileChoice - 1;
        double lengte = getDoubleInput();
        profileList.get(profileIndex).setGewicht(lengte);
    }



    private boolean preformPatientProfileEditorMenuAction() throws IOException, InterruptedException {

        switch (choice) {
            case 0 -> {exitZorgapp();           return false;}
            case 1 -> {runSetVoornaam();        return true;}
            case 2 -> {runSetActernaam();       return true;}
            case 3 -> {runSetLeeftijd();        return true;}
            case 4 -> {runSetGewicht();         return true;}
            case 5 -> {runSetLengte();          return true;}
            case 6 -> {runZorgverlenerMenu();   return true;}
            default ->{unknownError();          return false;}
        }
    }


//    private void runMedicijnvoorschriftMenu() {
//        while (!exit) {
//            printMedichijnvoorschriftMenu();
//
//        }
//
//    }


//    private void printPatientMenu() {
//        clearScreen();
//        System.out.println("\n Gebruiker: Patient");
//        System.out.println("1) Vul u voor- en achternaam in");
//        System.out.println("2) Ga terug");
//        System.out.println("0) Zorgapp uitschakelen");
//    }

//    private void printMedichijnvoorschriftMenu() {
//        int profileIndex = choice - 1;
//
//        Profile profile = profileList.get(profileIndex);
//        MedicineList medicineList = profile.getMedicijnlijst();
//
//        for(int medicineIndex = 0; medicineIndex < profileList.get(profileIndex).getMedicijnlijst().sizeOf(); medicineIndex++)
//        {
//            System.out.println(medicineIndex + 1 + ")" + " " + medicineList.get(medicineIndex).getNaam() + " " +  medicineList.get(medicineIndex).getMg() + "mg");
//            System.out.println("*************************************");
//            System.out.println("Medicijnbeschrijving:");
//            System.out.println("*************************************");
//        }
}
    



