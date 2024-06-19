package com.example.pharmacymanagementsystem;

public class DrugsData {
    private int drugID;
    private String  drugName;
    private int supplierId;
    private Double  unitPrice;
    private int quantity;

    public DrugsData(int drugID, String drugName, int supplierId, Double unitPrice, int quantity) {
        this.drugID = drugID;
        this.supplierId = supplierId;
        this.drugName = drugName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getDrugID() {
        return this.drugID;
    }

    public int getSupplierId() {
        return this.supplierId;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
