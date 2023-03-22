package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cabin_T3 {
    public static Scanner input = new Scanner(System.in);
    public static Cabin[] cabin = new Cabin[12];
    public static Passenger[] guestNo = new Passenger[12];
    public static Passenger[][] firstname = new Passenger[12][3];
    public static Passenger[][] surname = new Passenger[12][3];
    public static Passenger[] expenses = new Passenger[12];
    public static String[] WaitingList = new String[6];
    public static int t = 0;

    private static void Menu() {
        System.out.println("Enter 'A' to add a customer to a Cabin");
        System.out.println("Enter 'V' to view all Cabins");
        System.out.println("Enter 'E' to display empty Cabin ");
        System.out.println("Enter 'D' to delete customer from Cabin ");
        System.out.println("Enter 'F' to find Cabin from passenger name");
        System.out.println("Enter 'S' to store program data into file");
        System.out.println("Enter 'L' to load program data from file");
        System.out.println("Enter 'O' to view customers ordered alphabetically by name");
        System.out.println("Enter 'T' to view the expenses");
    }

    public static void initialise() {
        for (int x = 0; x < 12; x++) {
            cabin[x] = new Cabin("e", new Passenger[][]{new Passenger[]{new Passenger(0, "empty", "empty", 0)}});
        }
        for (int i = 0; i < 12; i++) {
            guestNo[i] = new Passenger(0, "empty", "empty", 0);
            expenses[i] = new Passenger(0, "empty", "empty", 0);
            for (int o = 0; o < 3; o++) {
                firstname[i][o] = new Passenger(0, "empty", "empty", 0);
                surname[i][o] = new Passenger(0, "empty", "empty", 0);
            }
        }
    }

    public static void AddPassenger() {
        int a = 0;
        for (int i = 0; i < 12; i++) {
            if (!(cabin[i].CruiseCabin.equals("e"))) {
                a += 1;
            }
        }
        if (a == 12) {
            System.out.println("Dear Customer, all our Cabins are currently full. You will be added to our waiting list or you have to try again later when the waiting list is full. Sorry for the inconvenience caused. ");
            if (t == 6){
                System.out.println("The Waiting-list is full !!!!. Please try again later");
                for (int i = 0; i < WaitingList.length; i++){
                    System.out.println(WaitingList[i]);
                }
            }else {
                System.out.println("Enter your Name so that you will be added to Waiting-list:");
                String cabin_name = input.next().toUpperCase();
                WaitingList[t] = cabin_name;
                t += 1;
                for (int i = 0; i < WaitingList.length; i++){
                    System.out.println(WaitingList[i]);
                }
            }
        } else {
            String s;
            System.out.println("Enter a Cabin number (within 0-11) :");
            int CabinNo = input.nextInt();
            if (CabinNo == 12) {
                System.out.println("Thank You. You are going back to menu");
                System.out.println("-----------------------------------------------------------");
                System.out.println("-----------------------------------------------------------");
                return;
            } else if (cabin[CabinNo].CruiseCabin.equals("e")) {
                System.out.println("Enter no of customers in the Cabin " + CabinNo + " 'Up to 3 customers only' :");
                try {
                    int no = Integer.parseInt(input.next());
                    if (no > 0 & no <= 3) {
                        System.out.println("Enter the Name for the Cabin " + CabinNo + " :");
                        String CabinName = input.next().toUpperCase();
                        guestNo[CabinNo].P_GuestCount = no;
                        String fName = null;
                        String sName = null;
                        for (int i = 0; i < no; i++) {
                            System.out.println("Enter First Name of the customer " + (i + 1) + " :");
                            fName = input.next().toUpperCase();
                            firstname[CabinNo][i].FirstNameCabin = fName;
                            System.out.println("Enter Surname of the customer " + (i + 1) + " :");
                            sName = input.next().toUpperCase();
                            surname[CabinNo][i].SurNameCabin = sName;
                        }
                        double amount = 250 * no;
                        expenses[CabinNo].TotalExpenses = (int) amount;
                        System.out.println("Thank You. Cabin " + CabinNo + " is occupied by " + CabinName);
                        cabin[CabinNo] = new Cabin(CabinName, new Passenger[][]{new Passenger[]{new Passenger(no, fName, sName, amount)}});
                    } else {
                        System.out.println("out of range");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong.");
                }
            } else {
                s = cabin[CabinNo].CruiseCabin;
                System.out.println("This Cabin has already been booked by " + s + ". PLEASE SELECT ANOTHER CABIN.");
            }
        }
    }

    public static void ViewCabin() {
        for (int x = 0; x < 12; x++) {
            if (cabin[x].CruiseCabin.equals("e")) {
                System.out.println("---------------------------------------------");
                System.out.println("Cabin " + x + " is empty");
                System.out.println("---------------------------------------------");
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Cabin " + x + " is occupied by : " + cabin[x].CruiseCabin);
                System.out.println("No of customers in Cabin " + x + " : " + guestNo[x].P_GuestCount);
                for (int i = 0; i < guestNo[x].P_GuestCount; i++) {
                    System.out.println("Full name of the customer " + (i + 1) + " : " + firstname[x][i].FirstNameCabin + " " + surname[x][i].SurNameCabin);
                }
                System.out.println("---------------------------------------------");
            }
        }
    }

    public static void EmptyDisplay(){
        for (int x = 0; x < 12; x++) {
            if (cabin[x].CruiseCabin.equals("e")){
                System.out.println("Cabin " + x + " is empty");
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public static void DeletePassenger(){
        System.out.println("Enter the Cabin Number that you want to delete a customer : ");
        int CabinNo = input.nextInt();
        if (cabin[CabinNo].CruiseCabin.equals("e")) {
            System.out.println("This Cabin is empty");
        } else {
            int a = 0;
            for (int i = 0; i < 8; i++) {
                if (!cabin[CabinNo].CruiseCabin.equals("e")) {
                    a += 1;
                }
            }
            cabin[CabinNo] = new Cabin("e", new Passenger[][]{new Passenger[]{new Passenger(0, "empty", "empty", 0)}});
            guestNo[CabinNo] = new Passenger(0, "empty", "empty", 0);
            expenses[CabinNo] = new Passenger(0, "empty", "empty", 0);
            for (int o = 0; o < 3; o++) {
                firstname[CabinNo][o] = new Passenger(0, "empty", "empty", 0);
                surname[CabinNo][o] = new Passenger(0, "empty", "empty", 0);
            }
            System.out.println("Deleted customer from Cabin " + CabinNo + " successfully");
        }
        if (WaitingList[0] != null) {
            System.out.println("Enter the details for the Name : " + WaitingList[0] + " for this Cabin No : " + CabinNo);
            System.out.println("Enter no of customers in the Cabin " + CabinNo + " 'Up to 3 customers only' :");
            try {
                int no = Integer.parseInt(input.next());
                if (no > 0 & no <= 3) {
                    String CabinName = WaitingList[0];
                    String[] tempArr = null;
                    tempArr = new String[WaitingList.length - 1];
                    for(int index = 0; index < 0; index++){
                        tempArr[index] = WaitingList[index];
                    }
                    for(int j = 0; j < WaitingList.length - 1; j++){
                        tempArr[j] = WaitingList[j+1];
                        WaitingList[j] = tempArr[j];
                    }
                    WaitingList[5] = null;
                    t -= 1;
                    guestNo[CabinNo].P_GuestCount = no;
                    String fName = null;
                    String sName = null;
                    for (int i = 0; i < no; i++) {
                        System.out.println("Enter firstname of the customer " + (i + 1) + " :");
                        fName = input.next().toUpperCase();
                        firstname[CabinNo][i].FirstNameCabin = fName;
                        System.out.println("Enter surname of the customer " + (i + 1) + " :");
                        sName = input.next().toUpperCase();
                        surname[CabinNo][i].SurNameCabin = sName;
                    }
                    double amount = 250 * no;
                    expenses[CabinNo].TotalExpenses = (int) amount;
                    System.out.println("Thank You. Cabin " + CabinNo + " is occupied by " + CabinName);
                    cabin[CabinNo] = new Cabin(CabinName, new Passenger[][]{new Passenger[]{new Passenger(no, fName, sName, amount)}});
                } else {
                    System.out.println("out of range");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }else{
            System.out.println("you can add a customer by going back to menu");
        }
    }

    public static void FindCabin(){
        System.out.println("Enter the customer name to find Cabin: ");
        String Name = input.next().toUpperCase();
        boolean isFound = false;
        for (int x = 0; x < 12; x++) {
            if (cabin[x].CruiseCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (firstname[x][0].FirstNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (firstname[x][1].FirstNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (firstname[x][2].FirstNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (surname[x][0].SurNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (surname[x][1].SurNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }else if (surname[x][2].SurNameCabin.equals(Name)) {
                System.out.println("Cabin Found. This particular customer is in Cabin " + x);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("customer not found.");
        }
    }

    public static void StoreFile(){
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
                myWriter.write("Cabin " + x + " is occupied by : " + cabin[x].CruiseCabin + newLine);
                myWriter.write("No of Passengers in Cabin " + x + " : " + guestNo[x].P_GuestCount + newLine);
                for (int i = 0; i < guestNo[x].P_GuestCount; i++) {
                    String s = firstname[x][i].FirstNameCabin + " " + surname[x][i].SurNameCabin + newLine;
                    myWriter.write("Full name of the passenger "+ (i+1) + " : " + s);
                }
                myWriter.write("******************************************************************************"+ newLine);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void LoadFile(){
        try {
            int count=0;
            File myObj = new File("CruiseShipCabinDetails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            System.out.println("Loaded successfully");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ViewOrderedPassenger(){
        int count = 0;
        for (int x = 0; x < 12; x++) {
            for (int i = 0; i < 3; i++) {
                if (!(firstname[x][i].FirstNameCabin.equals("empty"))) {
                    count += 1;
                }
            }
        }
        int c = 0;
        String[] alphabet = new String[count];
        for (int x = 0; x < 12; x++) {
            for (int i = 0; i < 3; i++) {
                if (!(firstname[x][i].FirstNameCabin.equals("empty"))) {
                    alphabet[c] = firstname[x][i].FirstNameCabin;
                    c += 1;
                }
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

    public static void TotalExpensesPassengers() {
        double tot = 0;
        System.out.println("The cost per Passenger is $250");
        for (int x = 0; x < 12; x++) {
            System.out.println("The Total Expenses for Cabin " + x + " : " +  "$" + " " + expenses[x].TotalExpenses);
        }
        for (int i = 0; i < 12; i++) {
            tot = tot + expenses[i].TotalExpenses;
        }
        System.out.println("The Total Expanses of all the customers in Ship is : " + "$" + " " + tot);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice;
        String choice2 = "Y";
        initialise(); //better to initialise in a procedure
        while (choice2.equals("Y")) {
            Menu();
            System.out.println("Enter your choice: ");
            choice = input.next().toUpperCase();
            switch (choice) {
                case "V":
                    ViewCabin();
                    break;
                case "A":
                    AddPassenger();
                    break;
                case "E":
                    EmptyDisplay();
                    break;
                case "D":
                    DeletePassenger();
                    break;
                case "F":
                    FindCabin();
                    break;
                case "S":
                    StoreFile();
                    break;
                case "L":
                    LoadFile();
                    break;
                case "O":
                    ViewOrderedPassenger();
                    break;
                case "T":
                    TotalExpensesPassengers();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            do {
                System.out.println("Do you want to go back to the main menu to choose another option?");
                System.out.println("Enter 'Y' for Yes or 'N' for No");
                choice2 = input.next().toUpperCase();
                if (choice2.equals("N")) {
                    System.out.println("Thank You. You are about to exit the program");
                    break;
                }
            }while (!(choice2.equals("Y")));

        }
    }
}

