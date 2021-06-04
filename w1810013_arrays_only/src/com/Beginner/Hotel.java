package com.Beginner;


import java.io.*;
import java.util.Scanner;

    public class Hotel {
        public static String firstName;
        public static String[] hotel = new String[9];
        static Scanner input = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
            initialise(hotel);
            while (true) {
                System.out.println("|--------------------HOTEL---------------------|");
                System.out.println("|options Available:                            |");
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
                System.out.print("Choice: " +"\n");
                String caseName = input.nextLine().toUpperCase();
                SwitchCase(caseName);
            }

        }

        //Initialising the hotel and making every room empty in the beginning
        private static void initialise( String hotelRef[] ) {
            for (int x = 1; x <9; x++ ) {
                hotelRef[x] = "Empty";
            }
        }

        //switch case method
        public static void SwitchCase(String caseName) throws IOException{
            switch (caseName){
                case "A":
                    viewEmptyRooms();
                    System.out.println("add members to room ");
                    System.out.print("Enter room number (1-8) :" );
                    int roomNum = input.nextInt();
                    while(true){
                        if (roomNum < 9 && roomNum!=0) {
                                break;
                            } else {
                                System.out.println("Invalid input- Maximum rooms available is 8");
                                System.out.print("Enter room number (1-8) :");
                                roomNum = input.nextInt();

                        }
                    }
                    AddCustomer(roomNum);
                    break;
                case "V":
                    viewAllRooms();
                    break;
                case "E":
                    viewEmptyRooms();
                    break;
                case "D":
                    viewAllRooms();
                    System.out.print("Enter the room number from which you want to remove customer(1-8): ");
                    int delRoom = input.nextInt();
                    while (true) {
                                if (delRoom>0 && delRoom<9) {
                                    break;
                                } else {
                                    System.out.println("Invalid room- Rooms available are from 1-8");
                                    System.out.print("Enter room number (1-8) :");
                                    delRoom = input.nextInt();
                                }
                        }


                    removeCustomer(delRoom);
                    break;
                case "F":
                    System.out.println("Find room from Customer name");
                    System.out.print("Enter Customer name: ");
                    String customerName = input.nextLine();
                    while (true) {
                        if(customerName.matches("^[a-z A-Z]*$")){
                            break;
                        }else{
                            System.out.println("Invalid Input");
                            System.out.print("Enter Customer name: ");
                            customerName = input.nextLine();



                        }
                    }
                    FindRoom(customerName);
                    break;
                case "S":
                    System.out.println("Stored the data to file");
                    FileWrite();
                    break;
                case "L":
                    System.out.println("loaded data from file");
                    FileRead();
                    break;
                case "O":
                    System.out.println("View guests Ordered alphabetically by name.");
                    SortArray(hotel);
                    break;
                case "Q":
                    System.out.println("Thank You!!..");
                    System.exit(0);
                    break;

            }
        }

        //add member method
    public static void AddCustomer(int roomNum){
        System.out.print("Enter first name for room " + roomNum +" :");
        firstName = input.next();
        if(firstName.matches("^[a-z A-Z]*$")) {
            System.out.print("Enter Surname for room " + roomNum + " :");
            String lastName = input.next();
            if(lastName.matches("^[a-z A-Z]*$")){
            System.out.print("Enter Credit Card No :");
            int creditNo = input.nextInt();
            System.out.print("Enter No of Guests for room :");
            int guestNum = input.nextInt();
            hotel[(roomNum)] = "Name: " + firstName + " " + lastName + "\n" + "Credit Card No: " + creditNo + "\n" + "No of guests in the Room: " + guestNum;
            System.out.println("Customer "+firstName+" "+lastName+ " has been added to room No: "+roomNum);
        }else{
                System.out.println("Invalid Input!");
            }
        }else {
            System.out.println("Invalid Input!");
        }
        }

    //removing a customer method
    public static void removeCustomer(int delRoom){
        System.out.println("Removed the customer from Room number " +delRoom);
        hotel[(delRoom)]= "Empty";
    }

    //method to view all rooms
    public static void viewAllRooms(){
        for (int x = 1; x < 9; x++ ) {
            System.out.println("---------------------DETAILS------------------------");
            System.out.println("Room No : "+x+" " +"\n"+hotel[x]+ "\n");
        }
    }
    //method to view only the empty rooms
    public static void viewEmptyRooms(){
        for (int x = 1; x <9; x++ )
        {
            if (hotel[x].equals("Empty")){
                System.out.println("room " + x + " is Empty");
            }
        }

    }
    //method to find the room No using customer Name
    public static void FindRoom(String firstName){
        boolean found = true;
        for (int x = 1; x <9; x++){
            if (hotel[x].contains(firstName)){
                System.out.println("The Room number for customer " +firstName + " is : " +x);
                found = true;
                break;
            }else {
                found = false;
            }
        }
        if (!found){
            System.out.println("\n person Not found - " +firstName +"!!\n");
        }


    }

    //Method to write file
    public static void FileWrite() throws IOException {
        File file= new File("Hotel.dat");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        for (int x=1; x<=8; x++){
            fileWriter.write(hotel[x]+"\n");

        }
        fileWriter.close();
    }
    //Method to Read file
    public static void FileRead() throws IOException{
        File file =new File("Hotel.dat");
        file.createNewFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);

        String st;
        while ((st = br.readLine()) != null){
            System.out.println("Done Successfully!!!");
            hotel = st.split("\\s+");
        }



    }
    //method to sort arrays in alphabetical Order
    public static void SortArray(String[] str){
        String temp;

        for (int j = 0; j < str.length; j++) {
            for (int i = j + 1; i < str.length; i++) {
                // comparing adjacent strings
                if (str[i].compareTo(str[j]) < 0) {
                    temp = str[j];
                    str[j] = str[i];
                    str[i] = temp;
                }
            }
            System.out.println(str[j]);
        }
    }

}

