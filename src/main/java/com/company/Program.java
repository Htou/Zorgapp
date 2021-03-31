package com.company;

import java.io.IOException;


public class Program {

    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here
        System.out.println(args[0] + " " + args[1]);
        ZorgApp menu = new ZorgApp(args);
        menu.runLanguageMenu();
    }
}
