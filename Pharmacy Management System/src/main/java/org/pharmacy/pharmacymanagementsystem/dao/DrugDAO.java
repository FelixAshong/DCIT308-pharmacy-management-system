package org.pharmacy.pharmacymanagementsystem.dao;

import org.pharmacy.pharmacymanagementsystem.model.Drug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO {

    public void addDrug(Drug drug) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO drugs (drug_name, description, price, stock) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, drug.getDrugName());
                stmt.setString(2, drug.getDescription());
                stmt.setDouble(3, drug.getPrice());
                stmt.setInt(4, drug.getStock());
                stmt.executeUpdate();
            }
        }
    }

    public List<Drug> getAllDrugs() throws SQLException {
        List<Drug> drugs = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM drugs";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    Drug drug = new Drug();
                    drug.setDrugId(rs.getInt("drug_id"));
                    drug.setDrugName(rs.getString("drug_name"));
                    drug.setDescription(rs.getString("description"));
                    drug.setPrice(rs.getDouble("price"));
                    drug.setStock(rs.getInt("stock"));
                    drugs.add(drug);
                }
            }
        }
        return drugs;
    }

    public Drug getDrugById(int drugId) throws SQLException {
        Drug drug = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM drugs WHERE drug_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, drugId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        drug = new Drug();
                        drug.setDrugId(rs.getInt("drug_id"));
                        drug.setDrugName(rs.getString("drug_name"));
                        drug.setDescription(rs.getString("description"));
                        drug.setPrice(rs.getDouble("price"));
                        drug.setStock(rs.getInt("stock"));
                    }
                }
            }
        }
        return drug;
    }

    public Drug getDrugByName(String drugName) throws SQLException {
        Drug drug = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM drugs WHERE drug_name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, drugName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        drug = new Drug();
                        drug.setDrugId(rs.getInt("drug_id"));
                        drug.setDrugName(rs.getString("drug_name"));
                        drug.setDescription(rs.getString("description"));
                        drug.setPrice(rs.getDouble("price"));
                        drug.setStock(rs.getInt("stock"));
                    }
                }
            }
        }
        return drug;
    }

    public void updateDrug(Drug drug) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE drugs SET drug_name = ?, description = ?, price = ?, stock = ? WHERE drug_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, drug.getDrugName());
                stmt.setString(2, drug.getDescription());
                stmt.setDouble(3, drug.getPrice());
                stmt.setInt(4, drug.getStock());
                stmt.setInt(5, drug.getDrugId());
                stmt.executeUpdate();
            }
        }
    }
}
