package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.DrugDAO;
import org.pharmacy.pharmacymanagementsystem.model.Drug;

import java.sql.SQLException;

public class SearchDrugFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Drug");
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Drug Name:");
        grid.add(nameLabel, 0, 0);
        TextField nameField = new TextField();

        grid.add(nameField, 1, 0);

        Button searchButton = new Button("Search");
        grid.add(searchButton, 1, 1);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        searchButton.setOnAction(e -> {
            String drugName = nameField.getText();
            try {
                Drug drug = drugDAO.getDrugByName(drugName);
                if (drug != null) {
                    resultArea.setText("Name: " + drug.getDrugName() + "\nDescription: " + drug.getDescription() + "\nPrice: " + drug.getPrice() + "\nStock: " + drug.getStock());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't retrieve drug, please check drug name for validity");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error searching for drug: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        vbox.getChildren().addAll(grid, resultArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
