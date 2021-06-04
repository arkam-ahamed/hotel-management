package com.Beginner;


public class Queue {

    private String queue[];

    public Queue(int size){
        this.queue = new String[size];
        for (int i = 0; i < size; i++){
            this.queue[i] = "";
        }
    }

    private int getSpot(){
        for (int i = 0; i < this.queue.length; i++){
            if (this.queue[i].equals("")){
                return i;
            }
        }
        return -1;
    }

    public boolean push(String s){
        int index = this.getSpot();
        if (index < 0){
            System.out.println("Queue is full. First customer is " + this.pop() + " and will be removed.");
            index = this.getSpot();
        }
        this.queue[index] = s;
        return true;
    }

    public String pop(){
        String item = this.queue[0];
        for (int i = 0; i < (this.queue.length - 1); i++){
            this.queue[i] = this.queue[i+1];
        }
        this.queue[this.queue.length-1] = "";
        return item;
    }

    public boolean avail(){
        return (this.getSpot() != 0);
    }

}

