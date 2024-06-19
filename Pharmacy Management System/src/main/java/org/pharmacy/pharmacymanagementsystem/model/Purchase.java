package org.pharmacy.pharmacymanagementsystem.model;

import java.time.LocalDateTime;

public class Purchase {
    private int purchaseId;
    private int drugId;
    private String drugName;
    private String customerName;
    private LocalDateTime purchaseDate;
    private int quantity;
    private double totalPrice;

    // Getters and setters for all fields
    public int getPurchaseId() { return purchaseId; }
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }

    public int getDrugId() { return drugId; }
    public void setDrugId(int drugId) { this.drugId = drugId; }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
