package com.Beginner;

public class CW02_Hotel_OOP {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(8);
        boolean start = true;
        while (start){
            start = hotel.start();
        }
        System.out.println("Thank You!!.");
    }

}

