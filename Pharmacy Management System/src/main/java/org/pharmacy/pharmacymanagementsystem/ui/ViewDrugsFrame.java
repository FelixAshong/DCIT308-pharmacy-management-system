package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.DrugDAO;
import org.pharmacy.pharmacymanagementsystem.model.Drug;


import java.sql.SQLException;
import java.util.List;


public class ViewDrugsFrame extends Application {

    private DrugDAO drugDAO = new DrugDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Drugs");

        TableView<Drug> table = new TableView<>();

        TableColumn<Drug, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getDrugId()).asObject());

        TableColumn<Drug, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDrugName()));

        TableColumn<Drug, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Drug, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        TableColumn<Drug, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        table.getColumns().addAll(idColumn, nameColumn, descColumn, priceColumn, stockColumn);

        try {
            List<Drug> drugs = drugDAO.getAllDrugs();
            table.getItems().addAll(drugs);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error retrieving drugs: " + e.getMessage());
            alert.showAndWait();
        }

        BorderPane root = new BorderPane();
        root.setCenter(table);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

