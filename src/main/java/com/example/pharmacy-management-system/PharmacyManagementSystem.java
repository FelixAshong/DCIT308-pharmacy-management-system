package com.example.pharmacymanagementsystem;


import java.sql.*;
import java.util.*;
import java.util.HashMap;



public class PharmacyManagementSystem {
    private Connection connection;
    private HashMap<Integer, Drug> drugs;
    private HashMap<Integer, Supplier> suppliers;
    private HashMap<Integer, Buyer> buyers;
    private ArrayList<Purchase> purchaseHistory;

    public PharmacyManagementSystem() {
        drugs = new HashMap<>();
        suppliers = new HashMap<>();
        buyers = new HashMap<>();
        purchaseHistory = new ArrayList<>();
    }

    //Function to search supplier by location
    public void searchSupplierByLocation(String location) {
        List<Integer> supplierId = new ArrayList<>();

        for (Supplier supplier : suppliers.values()) {
            if (supplier.getLocation().equalsIgnoreCase(location)) {
                supplierId.add(supplier.getId());
            }
        }

        for(int i = 0; i < supplierId.size(); i++){
            System.out.println(supplierId.get(i));
        }
    }

    //Function to add supplier to the hashtable
    public void addSupplier(String name, String contactNumber, String location, String email) {
        Supplier supplier = new Supplier(name, contactNumber, location, email);
        int supplierID = supplier.getId();
        suppliers.put(supplierID, supplier);
    }

    // Method to add drug
    public void addDrug(Drug drug) {
        drugs.put(drug.getId(), drug);
        System.out.println("Drug added successfully: " + drug.getDrugName() + ", " + drug.getSupplierName() + ", " + drug.getUnitPrice() + ", " + drug.getQuantity());
    }

    // Method to search drug
    public void searchDrug(String name) {
        boolean found = false;
        for (Map.Entry<Integer, Drug> entry : drugs.entrySet()) {
            Drug drug = entry.getValue();
            if (drug.getDrugName().equalsIgnoreCase(name)) {
                System.out.println("Drug found: " + drug.getDrugName() + ", " + drug.getSupplierName() + ", " + drug.getUnitPrice() + ", " + drug.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drug found with name: " + name);
        }
    }

    // Method to edit drug
    public void editDrug(String name, String supplierID, double unitPrice, int quantity) {
        boolean found = false;
        for (Map.Entry<Integer, Drug> entry : drugs.entrySet()) {
            Drug drug = entry.getValue();
            if (drug.getDrugName().equalsIgnoreCase(name)) {
                drug.setSupplierName(supplierID);
                drug.setUnitPrice(unitPrice);
                drug.setQuantity(quantity);
                System.out.println("Drug edited successfully: " + drug.getDrugName() + ", " + drug.getSupplierName() + ", " + drug.getUnitPrice() + ", " + drug.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drug found with name: " + name);
        }
    }

    //Method to delete drug
    public void deleteDrug(String name) {
        boolean found = false;
        Iterator<Map.Entry<Integer, Drug>> iterator = drugs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Drug> entry = iterator.next();
            Drug drug = entry.getValue();
            if (drug.getDrugName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Drug deleted successfully: " + drug.getDrugName() + ", " + drug.getSupplierName() + ", " + drug.getUnitPrice() + ", " + drug.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drug found with name: " + name);
        }
    }

    public void listDrugs() {
        System.out.println("Drug list");
        for (Map.Entry<Integer, Drug> entry : drugs.entrySet()) {
            Drug drug = entry.getValue();
            System.out.println(drug.getDrugName() + ", " + drug.getSupplierName() + ", " + drug.getUnitPrice() + ", " + drug.getQuantity());
        }
    }
    // Database connection setup
    public void connectToDatabase(String url, String username, String password) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    // method for searching supplier collection by location
    public List<Supplier> searchSupplierByLocation(List<Supplier> suppliers, String location) {
        List<Supplier> result = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            if (supplier.getLocation().equalsIgnoreCase(location)) {
                result.add(supplier);
            }
        }
        return result;
    }

    // method for searching supplier collection by name
    public List<Supplier> searchSupplierByName(List<Supplier> suppliers, String supplierName) {
        List<Supplier> results = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierName().equalsIgnoreCase(supplierName)) {
                results.add(supplier);
            }
        }

        return results;
    }


    // method that sorts purchase history based on date
    public void sortPurchaseHistoryByDate(List<Purchase> purchaseHistory) {
        Collections.sort(purchaseHistory, new Comparator<Purchase>() {
            @Override
            public int compare(Purchase purchase1, Purchase purchase2) {
                return purchase1.getPurchaseDate().compareTo(purchase2.getPurchaseDate());
            }
        });
    }


}