package com.Beginner;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Hotel {

    private Rooms room[];
    private Queue q = new Queue(7);
    private Scanner input = new Scanner(System.in);
    private String sortName[];

    public Hotel(int maxRooms) {
        this.room = new Rooms[maxRooms];
        for (int i = 0; i < maxRooms; i++) {
            this.room[i] = new Rooms();
        }

    }

    public boolean start() {
        if (this.selection()) {
            this.pause();
            return true;
        } else {
            return false;
        }
    }

    private boolean selection() {
        System.out.println("|--------------------HOTEL---------------------|");
        System.out.println("|Available options:                            |");
        System.out.println("|A: Add customer                               |");
        System.out.println("|D: Remove customer                            |");
        System.out.println("|E: Display Empty rooms                        |");
        System.out.println("|V: View all rooms                             |");
        System.out.println("|O: View Guests Ordered Alphabetically         |");
        System.out.println("|F: Find Room From Customer Name               |");
        System.out.println("|S: Save hotel                                 |");
        System.out.println("|L: Load hotel                                 |");
        System.out.println("|Q: Exit program                               |");
        System.out.println("|----------------------------------------------|");
        System.out.print("Choice: ");
        switch (input.next().toUpperCase()) {
            case "A":
                this.addCustomer();
                return true;
            case "D":
                this.removeCustomer();
                return true;
            case "E":
                this.PrintEmptyRoom();
                return true;
            case "V":
                this.viewAll();
                return true;
            case "O":
                this.viewOrdered();
                return true;
            case "F":
                this.findCustomer();
                return true;
            case "S":
                this.save();
                return true;
            case "L":
                this.load();
                return true;
            case "Q":
                return false;

            default:
                System.out.println("Invalid option, try again.");
                return true;
        }
    }

    private void addCustomer() {
        int roomNum = 0;
        String fName = "";
        String lName = "";
        int creditNum = 0;
        int guestCount = 0;

        int firstIndex = this.findFirstEmpty();
        if (firstIndex == -1) {
            System.out.println("There are no rooms left.");
            return;
        }
        while ((roomNum < 1) || (roomNum > 8)) {
            System.out.print("Room number (first empty is " + (firstIndex + 1) + "):");
            roomNum = this.input.nextInt();
            if ((roomNum < 1) || (roomNum > 8)) {
                System.out.println("Invalid room number, try again.");
            }
        }
        if (!this.room[roomNum - 1].getFirstName().equals("")) {
            System.out.println("This room is occupied.");
            return;
        }
        while (fName.equals("")) {
            System.out.print("Customer First name: ");
            fName = this.input.next();
            System.out.println("Customer Last Name: ");
            lName = this.input.next();
            System.out.println("Number of guests: ");
            guestCount = this.input.nextInt();
            System.out.println("Enter Credit Number: ");
            creditNum = this.input.nextInt();

        }

        this.room[roomNum - 1].setFName(fName);
        this.room[roomNum - 1].setLName(lName);
        this.room[roomNum - 1].setGuestCount(guestCount);
        this.room[roomNum - 1].setCreditNum(creditNum);
        this.q.push(fName);
        System.out.println("Customer " + fName+ " " +lName+ " has been added to room " + roomNum);
    }

    private void removeCustomer() {
        int roomNum = 0;
        String choice = "";
        while ((roomNum < 1) || (roomNum > 8)) {
            System.out.print("Room number to empty: ");
            roomNum = this.input.nextInt();
            if ((roomNum < 1) || (roomNum > 8)) {
                System.out.println("Invalid room number, try again.");
            }
        }
        if (this.room[roomNum - 1].getFirstName().equals("")) {
            System.out.println("This room is empty.");
            return;
        }
        System.out.println("You are emptying room number " + roomNum + " occupied by " + this.room[roomNum - 1].getFirstName() + ". Are you sure? (y/n)");
        while (!(choice.equals("y")) && !(choice.equals("n"))) {
            choice = this.input.next();
        }
        if (choice.equals("n")) {
            System.out.println("Aborting...");
        } else if (choice.equals("y")) {
            this.room[roomNum - 1].setFName("");
            System.out.println("Room " + roomNum + " is now empty.");
        }
    }

    private void PrintEmptyRoom() {
        for (int i = 0; i < this.room.length; i++) {
            if (this.room[i].getFirstName().equals("")) {
                System.out.println("Room " + (i + 1) + " is empty.");
            } else {
                System.out.println("Room " + (i + 1) +" is occupied for now..");

            }
        }
    }

    private void viewAll() {
        for (int i = 0; i < this.room.length; i++) {
            if (this.room[i].getFirstName().equals("")) {
                System.out.println("Room " + (i + 1) + " is empty.");
            } else {
                this.sortName = new String[this.room.length];
                this.sortName[i] = this.room[i].getFirstName();
                System.out.println("--------------------------------------");
                System.out.println("Room " + (i + 1) + " is occupied by " + this.room[i].getFirstName() + " " + this.room[i].getLastName() + "\n" + "Number of Guests input the room: " + this.room[i].getGuestCount() + "\n" + "credit card number is: " + this.room[i].getCreditNum());
                System.out.println("--------------------------------------");
            }
        }

    }

    private void viewOrdered() {
        System.out.println("The customer names sorted alphabetically are as follows:");
        String[] myArray = new String[this.room.length];
        for (int i = 0; i < this.room.length; i++) {
            myArray[i] = this.room[i].getFirstName();
        }

        Arrays.sort(myArray);
        for (int a = 0; a < myArray.length; a++) {
            System.out.println(myArray[a]);
        }
    }

    private void findCustomer() {
        String name;
        boolean f = false;
        System.out.println("Customer Name: ");
        name = input.next().toLowerCase();
        for (int i = 0; i < 8; i++) {
            if (this.room[i].getFirstName().toLowerCase().equals(name) || this.room[i].getLastName().toLowerCase().equals(name)) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Found Customer " + this.room[i].getFirstName() + " " + this.room[i].getLastName() + " in room No: " + (i + 1));
                System.out.println("-------------------------------------------------------------");
                f = true;
            }

        }
        if (!f) {
            System.out.println("No customer with this name!!");
        }

    }

    private void save() {
        System.out.print("Save name: ");
        String filename = input.next();
        System.out.println("Saving...");
        System.out.println("Saved!!");
        try {
            FileWriter w = new FileWriter(filename + ".hsave");
            w.write(this.room.length + " ");
            for (int i = 0; i < this.room.length; i++) {
                w.write(this.room[i].getFirstName() + " ");
            }
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(CW02_Hotel_OOP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() {
        System.out.print("Load file: ");
        String filename = input.next();
        System.out.println("Loading..." +"\n");
        try {
            Scanner r = new Scanner(new BufferedReader(new FileReader((filename + ".hsave"))));
            this.room = new Rooms[r.nextInt()];
            for (int i = 0; i < this.room.length; i++) {
                this.room[i] = new Rooms();
            }
            int x = 0;
            while (r.hasNext()) {
                this.room[x].setFName(r.next());
                System.out.println("-------------------"+"\n");
                System.out.println("Loaded room " + (x + 1) + "\n");
                System.out.println("-------------------");
                System.out.println("******--Details--******");
                System.out.println("Name: "+this.room[x].getFirstName()+" "+this.room[x].getLastName()+"\n"+"No of Guests: "+this.room[x].getGuestCount()+"\n"+"Credit Number: "+ this.room[x].getCreditNum());
                x++;
            }
            r.close();
        } catch (IOException ex) {
            Logger.getLogger(CW02_Hotel_OOP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private int findFirstEmpty() {
        for (int i = 0; i < this.room.length; i++) {
            if (this.room[i].getFirstName().equals("")) {
                return i;
            }
        }
        return -1;
    }

    private void pause() {
        System.out.print("Press ENTER to continue...");
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException ex) {
            Logger.getLogger(CW02_Hotel_OOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("");
    }


}