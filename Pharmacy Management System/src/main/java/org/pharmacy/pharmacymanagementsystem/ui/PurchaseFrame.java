package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.DrugDAO;
import org.pharmacy.pharmacymanagementsystem.dao.PurchaseDAO;
import org.pharmacy.pharmacymanagementsystem.model.Drug;
import org.pharmacy.pharmacymanagementsystem.model.Purchase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase Drug");
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Drug Name:");
        grid.add(nameLabel, 0, 0);

        ComboBox<String> drugComboBox = new ComboBox<>();
        grid.add(drugComboBox, 1, 0);

        // Populate the ComboBox with drug names from the database
        try {
            List<Drug> drugs = drugDAO.getAllDrugs();
            for (Drug drug : drugs) {
                drugComboBox.getItems().add(drug.getDrugName());
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading drugs: " + e.getMessage());
            alert.showAndWait();
        }

        Label qtyLabel = new Label("Quantity:");
        grid.add(qtyLabel, 0, 1);
        TextField qtyField = new TextField();
        grid.add(qtyField, 1, 1);

        Label customerNameLabel = new Label("Customer Name:");
        grid.add(customerNameLabel, 0, 2);
        TextField customerNameField = new TextField();
        grid.add(customerNameField, 1, 2);

        Button purchaseButton = new Button("Purchase");
        grid.add(purchaseButton, 1, 3);

        purchaseButton.setOnAction(e -> {
            String drugName = drugComboBox.getValue();
            String customerName = customerNameField.getText();
            int quantity;
            try {
                quantity = Integer.parseInt(qtyField.getText());
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid quantity.");
                alert.showAndWait();
                return;
            }

            try {
                Drug drug = drugDAO.getDrugByName(drugName);
                if (drug != null && drug.getStock() >= quantity) {
                    drug.setStock(drug.getStock() - quantity);
                    drugDAO.updateDrug(drug);

                    Purchase purchase = new Purchase();
                    purchase.setDrugId(drug.getDrugId());
                    purchase.setCustomerName(customerName);
                    purchase.setPurchaseDate(LocalDateTime.now());
                    purchase.setQuantity(quantity);
                    purchase.setTotalPrice(drug.getPrice() * quantity);
                    purchaseDAO.addPurchase(purchase);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Purchase successful!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough stock available or drug not found.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error processing purchase: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        vbox.getChildren().add(grid);
        Scene scene = new Scene(vbox, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
