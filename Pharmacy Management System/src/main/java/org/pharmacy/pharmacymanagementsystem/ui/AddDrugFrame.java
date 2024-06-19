package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.DrugDAO;
import org.pharmacy.pharmacymanagementsystem.model.Drug;

import java.sql.SQLException;

public class AddDrugFrame extends Application {

    private DrugDAO drugDAO = new DrugDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Drug");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 0);

        Label descLabel = new Label("Description:");
        grid.add(descLabel, 0, 1);
        TextField descField = new TextField();
        grid.add(descField, 1, 1);

        Label priceLabel = new Label("Price:");
        grid.add(priceLabel, 0, 2);
        TextField priceField = new TextField();
        grid.add(priceField, 1, 2);

        Label stockLabel = new Label("Stock:");
        grid.add(stockLabel, 0, 3);
        TextField stockField = new TextField();
        grid.add(stockField, 1, 3);

        Button addButton = new Button("Add");
        grid.add(addButton, 1, 4);

        addButton.setOnAction(e -> {
            try {
                Drug drug = new Drug();
                drug.setDrugName(nameField.getText());
                drug.setDescription(descField.getText());
                drug.setPrice(Double.parseDouble(priceField.getText()));
                drug.setStock(Integer.parseInt(stockField.getText()));

                drugDAO.addDrug(drug);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Drug added successfully!");
                alert.showAndWait();
                primaryStage.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error parsing number: " + ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();  // Log the exception stack trace
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding drug: " + ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();  // Log the exception stack trace
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Unknown error: " + ex.getMessage());
                alert.showAndWait();
                ex.printStackTrace();  // Log the exception stack trace
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

