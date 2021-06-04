package com.Beginner;


public class Rooms {

    private String firstName;
    private String lastName;
    private int creditNum;
    private int guestCount;

    public Rooms(){
        this.firstName = "";
        this.lastName = "";
        this.creditNum = 0;
        this.guestCount = 0;
    }


    public void setFName(String name){
        this.firstName = name;
    }

    public void setLName(String name){
        this.lastName = name;
    }
    public void setCreditNum(int creditNum){
        this.creditNum = creditNum;
    }


    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getCreditNum() {
        return this.creditNum;
    }

    public int getGuestCount() {
        return this.guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }
}

