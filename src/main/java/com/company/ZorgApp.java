package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;



public class ZorgApp {
    ProfileList profilest;
    boolean exit;

    public ZorgApp() {
        exit = false;
    }

    public void runMenu() throws IOException {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getInput();
            preformAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|            Welkom bij             |");
        System.out.println("|            de Zorgapp             |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        System.out.println("\nLogin: Kies een gebruiker");
        System.out.println("1) Zorgverlener");
        System.out.println("2) Patient");
        System.out.println("0) Zorgapp uitschakelen");
    }

    private int getInput() {
        Scanner kb = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 2) {
            try {
                System.out.println("\nKies een nummer uit de lijst: ");
                choice = Integer.parseInt(kb.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Verkeerde Input. Kies een nummer uit de lijst");
            }
        }
        return choice;
    }

    private void preformAction(int choice) throws IOException {
        switch (choice) {
            case 0 -> {
                exit = true;
                System.out.println("Bedankt voor het gebruiken van onze applicatie");
            }
            case 1 -> createProfileJson();
            case 2 -> pickShape();
            default -> System.out.println("Er trad een onbekende fout op");
        }

    }

    private void createProfileJson() throws IOException {
        Gson gson = new Gson();
        Profile profile = new Profile();
        profile.SetVoornaam("Hichem");
        profile.SetAchternaam("Touati");
        profile.SetLeeftijd(27);


        // 1. Java object to JSON file
        gson.toJson(profile, new FileWriter("/home/hichem/Dropbox/HU-ADSD/S1/ZorgApp/src/test/testfiles/test.json"));

        // 2. Java object to JSON string
//        String jsonInString = gson.toJson(obj);


        //TODO export profile to json and profile is an object




    }

    private void pickShape() {
        int num = (int) (Math.random() * 4);
        switch (num) {
            case 0 -> System.out.println("Vierkant");
            case 1 -> System.out.println("Cirkel");
            case 2 -> System.out.println("Triangel");
            case 3 -> System.out.println("Hexagon");
            default -> System.out.println("Pyramide");
        }
    }

}