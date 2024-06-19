package com.example.pharmacymanagementsystem;

public class Buyer {
    private static int count = 1;
    private String buyerName;
    private String contactNumber;
    private int id;

    public Buyer(String buyerName, String contactNumber) {
        this.buyerName = buyerName;
        this.contactNumber = contactNumber;
        this.id = count++;
    }

    // Getters and setters

    public int getId(){
        return this.id;
    }
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
