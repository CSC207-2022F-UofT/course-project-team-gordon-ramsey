package external_interface;

import java.util.Scanner;

public class CLI {

    public void master(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            boolean result = identifyUser();
        }

    }
    public boolean identifyUser(){
        System.out.println("Welcome!");
        return false;
    }

    public boolean login(){
        return false;
    }

    public boolean signup(){
        return false;
    }
}
