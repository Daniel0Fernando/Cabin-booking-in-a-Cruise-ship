package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice1;
        String choice2 = "Y";
        String[] CabinNam = new String[12];
        String[] FName = new String[12];
        String[] SName = new String[12];
        initialise(CabinNam,FName,SName); //initializing in a procedure
        while (choice2.equals("Y")) {
            Menu();
            System.out.println("Enter your choice: ");
            choice1 = input.next().toUpperCase();
            switch (choice1) {
                case "V":
                    ViewCabin(CabinNam, FName, SName);
                    break;
                case "A":
                    AddCustomer(CabinNam, FName, SName);
                    break;
                case "E":
                    EmptyCabins(CabinNam);
                    break;
                case "D":
                    DeleteCustomer(CabinNam, FName, SName);
                    break;
                case "F":
                    FindCustomer(CabinNam);
                    break;
                case "S":
                    StoreProgram(CabinNam, FName, SName);
                    break;
                case "L":
                    LoadProgram();
                    break;
                case "O":
                    ViewPassengerOrdered(CabinNam);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            do {
                System.out.println("Do you want to go back the main menu to choose another option?");
                System.out.println("Enter 'Y' for Yes or 'N' for No");
                choice2 = input.next().toUpperCase();
                if (choice2.equals("N")) {
                    System.out.println("Thank You. You are about to exit the program");
                    break;
                }
            }while (!(choice2.equals("Y")));

        }
    }
    private static void initialise(String[] CabinNam, String[] FName, String[] SName){
        for (int x = 0; x < 12; x++) {
            CabinNam[x] = "e";
            FName[x] = "empty";
            SName[x] = "empty";
        }
    }

    private static void Menu() {
        System.out.println("Enter 'A' to add a customer to Cabin");
        System.out.println("Enter 'V' to view all Cabins");
        System.out.println("Enter 'E' to display empty Cabins ");
        System.out.println("Enter 'D' to delete customer from Cabin ");
        System.out.println("Enter 'F' to find Cabin from customer name");
        System.out.println("Enter 'S' to store program data into file");
        System.out.println("Enter 'L' to load program data from file");
        System.out.println("Enter 'O' to view customers ordered alphabetically by name");
    }

    public static void AddCustomer(String[] CabinNam, String[] FName, String[] SName){
        Scanner input = new Scanner(System.in);
        String s;
        System.out.println("Enter a Cabin number (within 0-11) :");
        int CabinNum = input.nextInt();
        if (CabinNum == 12) {
            System.out.println("Thank You. You are going back to main menu");
            System.out.println("------------------------------------------");
            System.out.println("------------------------------------------");
            return;
        } else if (CabinNam[CabinNum].equals("e")) {
            System.out.println("Enter your first name for the Cabin " + CabinNum + ":");
            String fName = input.next().toUpperCase();
            CabinNam[CabinNum] = fName;
            FName[CabinNum] = fName;
            System.out.println("Enter your surname for the Cabin " + CabinNum + ":");
            String sName = input.next().toUpperCase();
            SName[CabinNum] = sName;
            System.out.println("Thank You. Cabin " + CabinNum + " is occupied by " + fName);
        } else {
            s = CabinNam[CabinNum];
            System.out.println("This Cabin has already been booked by " + s + ". PLEASE SELECT ANOTHER CABIN.");
        }
    }

    public static void ViewCabin(String[] CabinNam, String[] FName, String[] SName){
        for (int x = 0; x < 12; x++) {
            if (CabinNam[x].equals("e")) {
                System.out.println("---------------------------------------------");
                System.out.println("Cabin " + x + " is empty");
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Cabin " + x + " is occupied by : " + CabinNam[x]);
                System.out.println("First name of the customer : " + FName[x]);
                System.out.println("Surname of the customer : " + SName[x]);
                System.out.println("---------------------------------------------");
            }
        }
    }

    public static void EmptyCabins(String[] CabinNam){
        for (int x = 0; x < 12; x++) {
            if (CabinNam[x].equals("e")) {
                System.out.println("Cabin " + x + " is empty");
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public static void DeleteCustomer(String[] CabinNam, String[] FName, String[] SName){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the Cabin Number that you want to delete a customer : ");
        int CabinNum = input.nextInt();
        if (CabinNam[CabinNum].equals("e")) {
            System.out.println("The Cabin number is already empty");
        } else {
            CabinNam[CabinNum] = "e";
            FName[CabinNum] = "empty";
            SName[CabinNum] = "empty";
            System.out.println("Deleted customer from Cabin " + CabinNum + " successfully");
        }
    }

    public static void FindCustomer(String[] CabinNam){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the customer name to find Cabin: ");
        String Name = input.next().toUpperCase();
        boolean isFound = false;
        for (int x = 0; x < 12; x++) {
            if (CabinNam[x].equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Customer not found.");
        }
    }

    public static void StoreProgram(String[] CabinNam, String[] FName, String[] SName){
        try {
            File myObj = new File("CruiseShipCabinDetails.txt");
            String newLine = System.getProperty("line.separator");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("CruiseShipCabinDetails.txt");
            for (int x = 0; x < 12; x++) {
                myWriter.write("Cabin " + x + " is occupied by : " + CabinNam[x] + newLine);
                myWriter.write("First name of the customer : " + FName[x]+ newLine);
                myWriter.write("Surname of the customer : " + SName[x]+ newLine);
                myWriter.write("----------------------------------------------------------"+ newLine);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void LoadProgram(){
        try {
            File myObj = new File("CruiseShipCabinDetails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ViewPassengerOrdered(String[] CabinNam){
        int count = 0;
        for (int x = 0; x < 12; x++) {
            if (!(CabinNam[x].equals("e"))) {
                count += 1;
            }
        }
        int c = 0;
        String[] alphabet = new String[count];
        for (int x = 0; x < 12; x++) {
            if (!(CabinNam[x].equals("e"))) {
                alphabet[c] = CabinNam[x];
                c += 1;
            }
        }
        boolean isSwapped = true;
        while (isSwapped) {
            isSwapped = false;
            for (int i = 0; i < alphabet.length - 1; i++) {
                if (alphabet[i].compareToIgnoreCase(alphabet[i + 1]) > 0) {
                    String temp = alphabet[i + 1];
                    alphabet[i + 1] = alphabet[i];
                    alphabet[i] = temp;
                    isSwapped = true;
                }
            }

        }
        for (String value : alphabet) {
            System.out.println(value);
        }
    }
}
