package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.PurchaseDAO;
import org.pharmacy.pharmacymanagementsystem.model.Purchase;

import java.sql.SQLException;
import java.util.List;

public class PurchaseHistoryFrame extends Application {
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase History");
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        TableView<Purchase> tableView = new TableView<>();

        TableColumn<Purchase, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getPurchaseId()).asObject());

        TableColumn<Purchase, String> drugNameColumn = new TableColumn<>("Drug Name");
        drugNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDrugName()));

        TableColumn<Purchase, String> customerNameColumn = new TableColumn<>("Customer Name");
        customerNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCustomerName()));

        TableColumn<Purchase, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());

        TableColumn<Purchase, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTotalPrice()).asObject());

        TableColumn<Purchase, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPurchaseDate().toString()));

        tableView.getColumns().addAll(idColumn, drugNameColumn, customerNameColumn, quantityColumn, totalPriceColumn, dateColumn);

        try {
            List<Purchase> purchases = purchaseDAO.getAllPurchases();
            tableView.getItems().addAll(purchases);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading purchase history: " + e.getMessage());
            alert.showAndWait();
        }

        vbox.getChildren().add(tableView);
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

