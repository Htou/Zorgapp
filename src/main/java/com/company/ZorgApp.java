package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ZorgApp {
    private ProfileList profileList;
    private MedicineList medicineList;
    private WeightEntry weightEntry;

    private boolean exit;
    private boolean englishLanguage;

    private int choice;
    private int profileChoice;
    private int profileIndex;
    private int medicineChoice;
    private int medicineIndex;


    public ZorgApp() throws IOException {
        profileList = DataHandler.loadProfileList();
        medicineList = DataHandler.loadMedicineList();
        weightEntry = new WeightEntry();

        exit = false;
        choice = -1;
        profileChoice = -1;
        profileIndex = profileChoice - 1;
        medicineChoice = -1;
        medicineIndex = medicineChoice - 1;
        englishLanguage = false;
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void unknownError() {
        if (englishLanguage == true) {
            System.out.println("An unknown error has occured");
        } else {
            System.out.println("Er trad een onbekende fout op");
        }
    }

    private void exitZorgapp() {
        exit = true;
        if (englishLanguage == true) {
            System.out.println("Thanks for using our Zorgapp");
        } else {
            System.out.println("Bedankt voor het gebruiken van onze Zorgapp");
        }
    }

    /*
     * Choose language menu stack.
     */
    public void runLanguageMenu() throws IOException, InterruptedException {
        printLanguageHeader();
        while (!exit) {
            printLanguageMenu();
            choice = getLanguageMenuInput();
            preformLanguageMenuAction();
        }
    }

    private void printLanguageHeader() {
        System.out.println("+------------------------------------------------+");
        System.out.println("|            Welkom bij  / Welcome to            |");
        System.out.println("|            de Zorgapp /  the Zorgapp           |");
        System.out.println("+------------------------------------------------+");

    }

    private void printLanguageMenu() {
        System.out.println("\nKies een taal / Choose a language:");
        System.out.println("1) Nederlands");
        System.out.println("2) English");
        System.out.println("0) Zorgapp uitschakelen / Exit Zorgapp");
    }

    private int getLanguageMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;

        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nKies een nummer uit de lijst / Choose a number from the list: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst. / Wrong input, choose a number from the list:");
            }
        }
        return choice;
    }

    private void preformLanguageMenuAction() throws IOException, InterruptedException {
        switch (choice) {
            case 0 -> exitZorgapp();
            case 1 -> {
                englishLanguage = false;
                runMainMenu();
            }
            case 2 -> {
                englishLanguage = true;
                runMainMenu();
            }
            default -> unknownError();
        }

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
        if (englishLanguage == true) {
            System.out.println("+-----------------------------------+");
            System.out.println("|            welcome to             |");
            System.out.println("|            the Zorgapp            |");
            System.out.println("+-----------------------------------+");

        } else {
            System.out.println("+-----------------------------------+");
            System.out.println("|            Welkom bij             |");
            System.out.println("|            de Zorgapp             |");
            System.out.println("+-----------------------------------+");
        }
    }


    private void printMainMenu() {
        if (englishLanguage == true) {
            System.out.println("\nLoginScreen: Choose an user");
            System.out.println("1) Login as caregiver");
            System.out.println("2) Login as patient");
            System.out.println("0) Exit Zorgapp");
        } else {
            System.out.println("\nLogin: Kies een gebruiker");
            System.out.println("1) Inloggen als zorgverlener");
            System.out.println("2) Inloggen als patient");
            System.out.println("0) Zorgapp uitschakelen");
        }

    }

    private int getMainMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;

        while (choice < 0 || choice > 2) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nChoose a number from the list");
                } else {
                    System.out.println("\nKies een nummer uit de lijst: ");
                }
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong Input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
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
        if (englishLanguage == true) {
            System.out.println("\nUser: Caregiver");
            System.out.println("1) Show patient list");
            System.out.println("2) Go back");
            System.out.println("0) Exit zorgapp");
        } else {
            System.out.println("\nGebruiker: Zorgverlener");
            System.out.println("1) Geef patientenlijst weer");
            System.out.println("2) Ga terug");
            System.out.println("0) Zorgapp uitschakelen");
        }


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
        if (englishLanguage == true) {
            System.out.println("Patient list:");
        } else {
            System.out.println("Patientenlijst:");
        }

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
                if (englishLanguage == true) {
                    System.out.println("\nInput a patient number from the list to show its patient profile: ");
                } else {
                    System.out.println("\nVoer een patientnummer uit de lijst in om de desbetreffende patient weer te geven: ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong ingput, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
            }
        }
        return choice;
    }

    private void preformPatientListMenuAction() throws IOException, InterruptedException {
        if (profileChoice >= 0 || profileChoice <= profileList.sizeOf()) {
            runPatientProfileMenu();
        } else {
            if (englishLanguage == true) {
                System.out.println("Wrong input, choose a number from a list");
            } else {
                System.out.println("Verkeerde invoer, kies een patientnummer uit de lijst");
            }
        }
    }

    private void runPatientProfileMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfile();
            displayWeightGraph();
            printPatientProfileMenu();
            choice = getPatientProfileMenuInput();
            preformPatientProfileMenuAction(choice);
        }
    }

    private void printPatientProfile() {
        int profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);
        MedicineList medicineList = profile.getMedicijnlijst();

        if (englishLanguage == true) {
            System.out.println("Patient profile:");
            System.out.println("First Name:" + " " + profile.getVoornaam());
            System.out.println("Last Name:" + " " + profile.getAchternaam());
            System.out.println("Age:" + " " + profile.getLeeftijd());
            System.out.println("Current weight:" + " " + profile.getGewicht() + "kg");
            System.out.print("Weight history: ");
        } else {
            System.out.println("Patientenprofiel:");
            System.out.println("Voornaam:" + " " + profile.getVoornaam());
            System.out.println("Achternaam:" + " " + profile.getAchternaam());
            System.out.println("Leeftijd:" + " " + profile.getLeeftijd());
            System.out.println("Hudige gewicht:" + " " + profile.getGewicht() + "kg");
            System.out.print("gewichtengeschiedenis: ");
        }


        for (int i = 0; i < profile.getGewichtenlijst().sizeOf(); i++) {
            System.out.print("[" + profile.getGewichtenlijst().get(i).getData() + ": " + profile.getGewichtenlijst().get(i).getGewicht() + "kg] ");
        }

        if (englishLanguage == true) {
            System.out.println("\nLength:" + " " + profile.getLengte() + "m");
            System.out.println("BMI:" + " " + profile.getBmi());
            System.out.println("\nMedicine prescription:");
        } else {
            System.out.println("\nlengte:" + " " + profile.getLengte() + "m");
            System.out.println("BMI:" + " " + profile.getBmi());
            System.out.println("\nMedicijnvoorschrift:");
        }

        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println(medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");
        }
    }

    private void printPatientProfileMenu() {
        if (englishLanguage == true) {
            System.out.println("\n1) Edit patient records");
            System.out.println("2) Show/edit medicine prescription");
            System.out.println("3) Go back to the Caregiver menu");
            System.out.println("0) Exit Zorgapp");
        } else {
            System.out.println("\n1) Patientgegevens bewerken");
            System.out.println("2) Medicijnvoorschrift weergeven/bewerken");
            System.out.println("3) Ga terug naar zorgverlenermenu");
            System.out.println("0) Zorgapp uitschakelen");
        }

    }

    private void displayWeightGraph() {
        int profileIndex = profileChoice - 1;

        Profile profile = profileList.get(profileIndex);

        if (englishLanguage == true) {
            System.out.println("\nGraph: WeighHistory");
        } else {
            System.out.println("\nGrafiek: gewichtenlijst");
        }

        for (int i = 0; i < profile.getGewichtenlijst().sizeOf(); i++) {
            double weight = profile.getGewichtenlijst().get(i).getGewicht();

            int loop = (int) (weight / 4);

            if (loop > 60) {
                loop = 60;
            } //set loop to 60 if over 60

            //print out enters
            if (i != 0) {
                System.out.println();
            }

            //print out menu

            //System.out.print("[" + (date) + "]" + weight + "kg \t");
            System.out.print(profile.getGewichtenlijst().get(i).getData() + ": " + weight + "kg \t");
            IntStream.range(1, loop).forEach(s -> System.out.print("█")); // print out bar

        }
        System.out.println(" ");
    }

    private int getPatientProfileMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 3) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nChoose a number from a list");
                } else {
                    System.out.println("\nKies een nummer uit de lijst: ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, Kies een nummer uit de lijst");
                }
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

        if (englishLanguage == true) {
            System.out.println("Edit Patient profile:");
            System.out.println("1) Edit first name:" + " " + profile.getVoornaam());
            System.out.println("2) Edit last name:" + " " + profile.getAchternaam());
            System.out.println("3) Edit Age:" + " " + profile.getLeeftijd());
            System.out.println("4) Add a new weight, current weight:" + profile.getGewicht() + "kg");
            System.out.println("5) Edit length:" + " " + profile.getLengte() + "m");
            System.out.println("+-----------------------------------------------------+");
            System.out.println("6) Go back to patient profile");
            System.out.println("0) Exit zorgapp");
        } else {
            System.out.println("Patientenprofiel bewerken:");
            System.out.println("1) Bewerk voornaam:" + " " + profile.getVoornaam());
            System.out.println("2) Bewerk achternaam:" + " " + profile.getAchternaam());
            System.out.println("3) Bewerk leeftijd:" + " " + profile.getLeeftijd());
            System.out.println("4) Voeg een gewicht toe, huidig gewicht:" + profile.getGewicht() + "kg");
            System.out.println("5) Bewerk lengte:" + " " + profile.getLengte() + "m");
            System.out.println("+-----------------------------------------------------+");
            System.out.println("6) Ga terug naar patientenprofiel");
            System.out.println("0) Zorgapp uitschakelen");
        }
    }

    private int getPatientProfileEditorMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 6) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nChoose a number from the list");
                } else {
                    System.out.println("\nKies een nummer uit de lijst: ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from te list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
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

        if (englishLanguage = true) {
            System.out.println("Input the first name of the patient");
        } else {
            System.out.println("Vul voornaam van patient in:");
        }

        String voornaam = getStringInput();
        profileList.get(profileIndex).setVoornaam(voornaam);
    }

    private void runSetActernaam() {
        profileIndex = profileChoice - 1;

        if (englishLanguage == true) {
            System.out.println("Input the last name of the patient");
        } else {
            System.out.println("Vul achternaam van patient in:");
        }

        String achternaam = getStringInput();
        profileList.get(profileIndex).setAchternaam(achternaam);
    }

    private void runSetLeeftijd() {
        profileIndex = profileChoice - 1;

        if (englishLanguage == true) {
            System.out.println("Input the age of the patient");
        } else {
            System.out.println("Vul leeftijd van patient in:");
        }

        int leeftijd = getIntegerInput();
        profileList.get(profileIndex).setLeeftijd(leeftijd);
    }

    private void runAddGewicht() {
        profileIndex = profileChoice - 1;
        Profile profile = profileList.get(profileIndex);

        if (englishLanguage == true) {
            System.out.println("Input the weight of the patient in kilograms (example: 65.43)");
        } else {
            System.out.println("Vul gewicht van patient in kilograms (voorbeeld: 65.53):");
        }

        double gewicht = getDoubleInput();

        weightEntry.setGewicht(gewicht);
        weightEntry.setData(LocalDate.now());
        profile.setGewicht(gewicht);
        profile.getGewichtenlijst().add(weightEntry);
    }


    private void runSetLengte() {
        profileIndex = profileChoice - 1;
        if (englishLanguage == true) {
            System.out.println("Input the length of the patient in meters (example 1.85)");
        } else {
            System.out.println("Vul lengte van patient in meters (voorbeeld: 1.85):");
        }

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
                runAddGewicht();
                return true;
            }
            case 5 -> {
                runSetLengte();
                return true;
            }
            case 6 -> {
                runPatientProfileMenu();
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

        if (englishLanguage == true) {
            System.out.println("Profile:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
            System.out.println("Medicine prescription:");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        } else {
            System.out.println("Profiel:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
            System.out.println("Medicijnvoorschrift:");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        }


        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println(medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");

            if (englishLanguage == true) {
                System.out.println("Description:" + " " + medicineList.get(i).getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            } else {
                System.out.println("Beschrijving:" + " " + medicineList.get(i).getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            }
        }
    }

    private void printMedichijnvoorschriftEditorMenu() {
        if (englishLanguage == true) {
            System.out.println("\nEdit medicine prescription:");
            System.out.println("1) Add a medicine");
            System.out.println("2) Edit prescription amount of medicine in (mg)");
            System.out.println("3) Delete a medicine from prescription");
            System.out.println("+-----------------------------------------------------+");
            System.out.println("4) Go back to patient profile");
            System.out.println("0) Exit Zorgapp");
        } else {
            System.out.println("\nMedicijnvoorschrift bewerken:");
            System.out.println("1) Voeg een medicijn toe");
            System.out.println("2) Bewerk medicijnhoeveelheid (mg)");
            System.out.println("3) Verwijder een medicijn vam medicijn voorschrift");
            System.out.println("+-----------------------------------------------------+");
            System.out.println("4) Ga terug naar patientenprofiel");
            System.out.println("0) Zorgapp uitschakelen");
        }
    }

    private int getMedicijnVoorschriftEditorMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 4) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nChoose a number from the list");
                } else {
                    System.out.println("\nKies een nummer uit de lijst: ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                }
                System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
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
        if (englishLanguage == true) {
            System.out.println("Medicine list");
        } else {
            System.out.println("Medicijnlijst:");
        }


        for (int i = 0; i < medicineList.sizeOf(); i++) {
            Medicine medicine = medicineList.get(i);
            System.out.println((i + 1) + ")" + " " + medicine.getNaam());

            if (englishLanguage == true) {
                System.out.println("Description:" + " " + medicine.getBeschrijving() + " " + medicine.getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            } else {
                System.out.println("Beschrijving:" + " " + medicine.getBeschrijving() + " " + medicine.getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            }
        }

    }

    private int getMedicijnListMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > medicineList.sizeOf()) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nInput a medicine number from the list to add to the medicine prescription");
                } else {
                    System.out.println("\nVoer een medicijnnummer uit de lijst in om het medicijn aan de medicijnvoorschrift toe te voegen:");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
            }
        }
        return choice;
    }

    private void addMedicijnToProfile() {
        profileIndex = profileChoice - 1;
        medicineIndex = medicineChoice - 1;

        Profile profile = profileList.get(profileIndex);
        Medicine medicine = medicineList.get(medicineIndex);

        if (englishLanguage == true) {
            System.out.println("Input the mg of the medicine");
        } else {
            System.out.println("Voer mg van medicijn in");
        }

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

        if (englishLanguage == true) {
            System.out.println("Profile:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
            System.out.println("Medicine description:");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        } else {
            System.out.println("Profiel:" + " " + profile.getVoornaam() + " " + profile.getAchternaam());
            System.out.println("Medicijnvoorschrift:");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        }


        for (int i = 0; i < profile.getMedicijnlijst().sizeOf(); i++) {
            System.out.println((i + 1) + ")" + " " + medicineList.get(i).getNaam() + " " + medicineList.get(i).getMg() + "mg");

            if (englishLanguage == true) {
                System.out.println("Description:" + " " + medicineList.get(i).getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            } else {
                System.out.println("Beschrijving:" + " " + medicineList.get(i).getBeschrijving());
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            }
        }
    }


    private int getMedicijnListMenuMGInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 1 || choice > medicineList.sizeOf()) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nInput a medicine number to edit the prescription amount in mg");
                } else {
                    System.out.println("\nVoer een medicijnnummer om de hoeveelheid in 'mg' te bewerken; ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
            }
        }
        return choice;


    }

    private void setMedicijnMgToMedicine() {
        profileIndex = profileChoice - 1;
        medicineIndex = medicineChoice - 1;

        Profile profile = profileList.get(profileIndex);

        if (englishLanguage == true) {
            System.out.println("Input the mg of the medicine");
        } else {
            System.out.println("Voer mg van medicijn in");
        }

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
                if (englishLanguage == true) {
                    System.out.println("\nInput a medicine number to delete this");
                } else {
                    System.out.println("\nVoer een medicijnnummer in om deze te verwijderen; ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {

                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }
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
                runPatientProfileMenu();
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
        if (englishLanguage == true) {
            System.out.println("\nLogin: Patient");
            System.out.println("1) Input your first and last name:");
            System.out.println("2) Go back");
            System.out.println("0) Exit Zorgapp");
        } else {
            System.out.println("\nLogin: Patient");
            System.out.println("1) Vul uw voor- en achternaam in:");
            System.out.println("2) Ga terug");
            System.out.println("0) Zorgapp uitschakelen");
        }

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
        if (englishLanguage == true) {
            System.out.println("Input your first name: ");
        } else {
            System.out.println("Vul uw voornaam in: ");
        }

        String input = getStringInput();
        String capitalizedInput = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

        return capitalizedInput;
    }

    private String getCareRecipientAchternaamInput() {
        if (englishLanguage == true) {
            System.out.println("Input your last name: ");
        } else {
            System.out.println("Vul uw achternaam in: ");
        }

        String input = getStringInput();
        String capitalizedInput = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

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
//            else {
//                System.out.println("gebruiker niet gevonden");
//                runCareRecipientMenu();
//            }

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
    }

    private void runCareRecipientProfileMenu() throws IOException, InterruptedException {
        while (!exit) {
            printPatientProfile();
            displayWeightGraph();
            printCareRecipientProfileMenu();
            choice = getCareRecipientProfileMenuInput();
            boolean dataWritten = preformCareRecipientProfileMenuAction();
            if (dataWritten) {
                DataHandler.saveProfileList(profileList);
            }
        }

    }


    private void printCareRecipientProfileMenu() {
        if (englishLanguage == true) {
            System.out.println("\n1) Edit first name");
            System.out.println("2) Go back to main menu");
            System.out.println("0) Exit ZorgApp");
        } else {
            System.out.println("\n1) Bewerk voornaam");
            System.out.println("2) Ga terug naar hoofdmenu");
            System.out.println("0) Zorgapp uitschakelen");
        }
    }

    private int getCareRecipientProfileMenuInput() {
        Scanner kb = new Scanner(System.in);
        choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                if (englishLanguage == true) {
                    System.out.println("\nChoose a number from the list: ");
                } else {
                    System.out.println("\nKies een nummer uit de lijst: ");
                }

                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                if (englishLanguage == true) {
                    System.out.println("Wrong input, choose a number from the list");
                } else {
                    System.out.println("Verkeerde invoer, kies een nummer uit de lijst");
                }

            }
        }
        return choice;
    }

    private void runSetVoornaamCareRecipient() {
        profileIndex = profileChoice - 1;

        if (englishLanguage == true) {
            System.out.println("Input a new first name: ");
        } else {
            System.out.println("Vul een nieuwe voornaam in: ");
        }

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
            default -> {
                unknownError();
                return false;
            }
        }
    }
}


