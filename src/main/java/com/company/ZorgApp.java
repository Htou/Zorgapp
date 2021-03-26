package com.company;

import java.io.IOException;
import java.util.Scanner;

public class ZorgApp {
    ProfileList profileList;
    MedicineList medicineList;
    boolean exit;

    int choice;
    int profileChoice;
    int profileIndex;
    int medicineChoice;
    int medicineIndex;

    public ZorgApp() throws IOException {
        profileList = DataHandler.loadProfileList();
        medicineList = DataHandler.loadMedicineList();

        exit = false;
        choice = -1;
        profileChoice = -1;
        profileIndex = profileChoice - 1;
        medicineChoice = -1;
        medicineIndex = medicineChoice - 1;
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


    /*
     * Main Menu stack.
     */
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
            case 2 -> runCareRecipientMenu();
            default -> unknownError();
        }

    }

    /*
     * Zorgverlener Menustack.
     */
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
        System.out.println("Patientenlijst:");

        for (int i = 0; i < profileList.sizeOf(); i++) {
            Profile profile = profileList.get(i);
            System.out.println((i + 1) + ")" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
        }
    }

    private int getPatientListMenuInput() {
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

        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println(medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");
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
        while (choice < 0 || choice > 3) {
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
            case 2 -> runMedicijnVoorschriftEditorMenu();
            case 3 -> runZorgverlenerMenu();
            default -> unknownError();
        }
    }

    private void runPatientProfileEditorMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfileEditorMenu();
            choice = getPatientProfileEditorMenuInput();
            boolean dataWritten = preformPatientProfileEditorMenuAction();

            if (dataWritten) {
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
        System.out.println("Vul lengte van patient in meters (voorbeeld: 1.85):");
        profileIndex = profileChoice - 1;
        double lengte = getDoubleInput();
        profileList.get(profileIndex).setLengte(lengte);
    }


    private boolean preformPatientProfileEditorMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> {
                exitZorgapp();
                return false;
            }
            case 1 -> {
                runSetVoornaam();
                return true;
            }
            case 2 -> {
                runSetActernaam();
                return true;
            }
            case 3 -> {
                runSetLeeftijd();
                return true;
            }
            case 4 -> {
                runSetGewicht();
                return true;
            }
            case 5 -> {
                runSetLengte();
                return true;
            }
            case 6 -> {
                runZorgverlenerMenu();
                return false;
            }
            default -> {
                unknownError();
                return false;
            }
        }
    }

    private void runMedicijnVoorschriftEditorMenu() throws IOException, InterruptedException {
        while (!exit) {
            printMedichijnVoorschriftList();
            printMedichijnvoorschriftEditorMenu();
            choice = getMedicijnVoorschriftEditorMenuInput();
            boolean dataWritten = preformMedicijnvoorschriftEditorMenuAction();

            if (dataWritten) {
                DataHandler.saveProfileList(profileList);
            }
        }

    }

    private void printMedichijnVoorschriftList() {
        profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);
        MedicineList medicineList = profile.getMedicijnlijst();

        System.out.println("Profiel:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
        System.out.println("Medicijnvoorschrift:");
        System.out.println("*****************************************************************************************************");

        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println(medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");
            System.out.println("Beschrijving:" + " " + medicineList.get(i).getBeschrijving());
            System.out.println("*****************************************************************************************************");
        }
    }

    private void printMedichijnvoorschriftEditorMenu() {
        System.out.println("\nMedicijnvoorschrift bewerken:");
        System.out.println("1) Voeg een medicijn toe");
        System.out.println("2) Bewerk medicijnhoeveelheid (mg)");
        System.out.println("3) Verwijder een medicijn");
        System.out.println("4) Ga terug naar zorgverlenermenu");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private int getMedicijnVoorschriftEditorMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 4) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer. Kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void runAddMedicijn() throws IOException, InterruptedException {
        while (!exit) {
            printMedicijnListMenu();
            medicineChoice = getMedicijnListMenuInput();
            addMedicijnToProfile();
            runMedicijnVoorschriftEditorMenu();
        }
    }

    private void printMedicijnListMenu() {
        System.out.println("Medicijnlijst:");

        for (int i = 0; i < medicineList.sizeOf(); i++) {
            Medicine medicine = medicineList.get(i);
            System.out.println((i + 1) + ")" + " " + medicine.getNaam());
            System.out.println("Beschrijving:" + " " + medicine.getBeschrijving() + " " + medicine.getBeschrijving());
            System.out.println("*****************************************************************************************************");
        }

    }

    private int getMedicijnListMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > medicineList.sizeOf()) {
            try {
                System.out.println("\nVoer een medicijnnummer uit de lijst in om het medicijn aan de medicijnvoorschrift toe te voegen: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void addMedicijnToProfile() {
        profileIndex = profileChoice - 1;
        medicineIndex = medicineChoice - 1;

        Profile profile = profileList.get(profileIndex);
        Medicine medicine = medicineList.get(medicineIndex);

        System.out.println("Voer mg van medicijn");
        int mg = getIntegerInput();

        medicine.setMg(mg);
        profile.getMedicijnlijst().add(medicine);
    }


    private void runEditMedicijnMg() throws IOException, InterruptedException {
        while (!exit) {
            printMedicijnvoorschriftMenu();
            medicineChoice = getMedicijnListMenuMGInput();
            setMedicijnMgToMedicine();
            runMedicijnVoorschriftEditorMenu();
        }
    }

    private void printMedicijnvoorschriftMenu() {
        profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);
        MedicineList medicineList = profile.getMedicijnlijst();

        System.out.println("Profiel:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
        System.out.println("Medicijnvoorschrift:");
        System.out.println("*****************************************************************************************************");

        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println((i + 1) + ")" + " " + medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");
            System.out.println("Beschrijving:" + " " + medicineList.get(i).getBeschrijving());
            System.out.println("*****************************************************************************************************");
        }
    }


    private int getMedicijnListMenuMGInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > medicineList.sizeOf()) {
            try {
                System.out.println("\nVoer een medicijnnummer om de hoeveelheid in 'mg' te bewerken; ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
            }
        }
        return choice;


    }

    private void setMedicijnMgToMedicine() {
        profileIndex = profileChoice - 1;
        medicineIndex = medicineChoice - 1;

        Profile profile = profileList.get(profileIndex);

        System.out.println("Voer mg van medicijn in");
        int mg = getIntegerInput();

        profile.getMedicijnlijst().get(medicineIndex).setMg(mg);
    }


    private void runRemoveMedicijn() throws IOException, InterruptedException {
        while (!exit) {
            printMedicijnvoorschriftMenu();
            medicineChoice = getMedicijnListMenuRemoveInput();
            removeMedicijnFromMedicine();
            runMedicijnVoorschriftEditorMenu();
        }
    }

    private void removeMedicijnFromMedicine() {
        profileIndex = profileChoice - 1;
        medicineIndex = medicineChoice - 1;

        Profile profile = profileList.get(profileIndex);

        profile.getMedicijnlijst().remove(medicineIndex);
    }

    private int getMedicijnListMenuRemoveInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > medicineList.sizeOf()) {
            try {
                System.out.println("\nVoer een medicijnnummer in om deze te verwijderen; ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private boolean preformMedicijnvoorschriftEditorMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> {
                exitZorgapp();
                return false;
            }
            case 1 -> {
                runAddMedicijn();
                return true;
            }
            case 2 -> {
                runEditMedicijnMg();
                return true;
            }
            case 3 -> {
                runRemoveMedicijn();
                return true;
            }
            case 4 -> {
                runZorgverlenerMenu();
                return false;
            }
            default -> {
                unknownError();
                return false;
            }
        }
    }


    /*
     * Consumer Care Recipient menu stack.
     */
    private void runCareRecipientMenu() throws IOException, InterruptedException {
        while (!exit) {
            printCareRecipientMenu();
            choice = getMainMenuInput();
            preformCareRecipientMenuAction();
        }
    }

    private void printCareRecipientMenu() {

        System.out.println("\nGebruiker: Patient");
        System.out.println("1) Vul uw voor- en achternaam in:");
        System.out.println("2) Ga terug");
        System.out.println("0) Zorgapp uitschakelen");
    }


    private void preformCareRecipientMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> exitZorgapp();
            case 1 -> runProfileSearch();
            case 2 -> runMainMenu();
            default -> unknownError();
        }
    }

    private void runProfileSearch() throws IOException, InterruptedException {
        String voornaam = getCareRecipientVoornaamInput();
        String achternaam = getCareRecipientAchternaamInput();
        searchProfileOnName(voornaam, achternaam);
        runCareRecipientProfileMenu();
    }


    private String getCareRecipientVoornaamInput() {
        System.out.println("Vul uw voornaam in");

        String input = getStringInput();
        String capitalizedInput = input.substring(0, 1).toUpperCase() + input.substring(1);

        return capitalizedInput;
    }

    private String getCareRecipientAchternaamInput() {
        System.out.println("Vul uw achternaam in:");

        String input = getStringInput();
        String capitalizedInput = input.substring(0, 1).toUpperCase() + input.substring(1);

        return capitalizedInput;
    }

    // (Binary) search
    private void searchProfileOnName(String voornaam, String achternaam) throws IOException, InterruptedException {

        for (int i = 0; i < profileList.sizeOf(); i++) {
            if (voornaam.equals(profileList.get(i).getVoornaam()) && achternaam.equals(profileList.get(i).getAchternaam())) {
                profileChoice = i + 1;
                runCareRecipientProfileMenu();
                break;
            }
//            else if (voornaam != profileList.get(i).getVoornaam() || achternaam != profileList.get(i).getAchternaam()) {
//                System.out.println("gebruiker niet gevonden");

//            int mini = 0;
//            int maxi = profileList.sizeOf();
//            profileIndex = ((mini + maxi) / 2);
//            Profile profile = profileList.get(profileIndex);
//
//
//            while (mini <= maxi) {
//                profileIndex = (mini + maxi) / 2;
//            }
//
//            if (voornaam == profile.getVoornaam() || achternaam == profile.getAchternaam()) {
//                System.out.println(profile.getVoornaam() + profile.getAchternaam());
//            } else if (voornaam != profile.getVoornaam() || achternaam != profile.getAchternaam()) {
//                mini = profileIndex + 1;
//            } else {
//                maxi = profileIndex - 1;
//            }
//
//            System.out.println(profile.getVoornaam() + " " + profile.getAchternaam());

        }
        System.out.println("gebruiker niet gevonden");
        runCareRecipientMenu();
    }

    private void runCareRecipientProfileMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfile();
            printCareRecipientProfileMenu();
            choice = getCareRecipientProfileMenuInput();
            boolean dataWritten = preformCareRecipientProfileMenuAction();
            if (dataWritten) {
                DataHandler.saveProfileList(profileList);
            }
        }

    }


    private void printCareRecipientProfileMenu() {
        System.out.println("\n1) Bewerk voornaam:");
        System.out.println("2) Ga terug naar hoofdmenu");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private int getCareRecipientProfileMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst:");
            }
        }
        return choice;
    }

    private void runSetVoornaamCareRecipient() {
        profileIndex = profileChoice - 1;
        System.out.println("Vul een nieuwe voornaam in:");
        String voornaam = getStringInput();
        profileList.get(profileIndex).setVoornaam(voornaam);
    }


    private boolean preformCareRecipientProfileMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> {
                exitZorgapp();
                return false;
            }
            case 1 -> {
                runSetVoornaamCareRecipient();
                return true;
            }
            case 2 -> {
                runMainMenu();
                return false;
            }
            default -> {unknownError(); return false;}
        }
    }

}


