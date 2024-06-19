package org.pharmacy.pharmacymanagementsystem.dao;

import org.pharmacy.pharmacymanagementsystem.model.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

    // Method to add purchase
    public void addPurchase(Purchase purchase) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO purchases (drug_id, customer_name, purchase_date, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, purchase.getDrugId());
                stmt.setString(2, purchase.getCustomerName());
                stmt.setTimestamp(3, Timestamp.valueOf(purchase.getPurchaseDate()));
                stmt.setInt(4, purchase.getQuantity());
                stmt.setDouble(5, purchase.getTotalPrice());
                stmt.executeUpdate();
            }
        }
    }

    // Method to get purchases by drug ID
    public List<Purchase> getPurchasesByDrugId(int drugId) throws SQLException {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchases WHERE drug_id = ? ORDER BY purchase_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, drugId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getInt("purchase_id"));
                purchase.setDrugId(rs.getInt("drug_id"));
                purchase.setCustomerName(rs.getString("customer_name"));
                purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setTotalPrice(rs.getDouble("total_price"));
                purchases.add(purchase);
            }
        }
        return purchases;
    }

    // Method to get all purchases
    public List<Purchase> getAllPurchases() throws SQLException {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT p.purchase_id, p.drug_id, p.customer_name, p.purchase_date, p.quantity, p.total_price, d.drug_name AS drug_name " +
                "FROM purchases p " +
                "JOIN drugs d ON p.drug_id = d.drug_id " +
                "ORDER BY p.purchase_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getInt("purchase_id"));
                purchase.setDrugId(rs.getInt("drug_id"));
                purchase.setCustomerName(rs.getString("customer_name"));
                purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setTotalPrice(rs.getDouble("total_price"));
                purchase.setDrugName(rs.getString("drug_name"));  // Assume you have this setter in Purchase class
                purchases.add(purchase);
            }
        }
        return purchases;
    }
}
