package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        Button addDrugButton = new Button("Add Drug");
        Button viewDrugsButton = new Button("View Drugs");
        Button searchDrugButton = new Button("Search Drug");
        Button purchaseDrugButton = new Button("Purchase Drug");
        Button purchaseHistoryButton = new Button("View Purchase History");

        addDrugButton.setOnAction(e -> new AddDrugFrame().start(new Stage()));
        viewDrugsButton.setOnAction(e -> new ViewDrugsFrame().start(new Stage()));
        searchDrugButton.setOnAction(e -> new SearchDrugFrame().start(new Stage()));
        purchaseDrugButton.setOnAction(e -> new PurchaseFrame().start(new Stage()));
        purchaseHistoryButton.setOnAction(e -> new PurchaseHistoryFrame().start(new Stage()));

        vbox.getChildren().addAll(addDrugButton, viewDrugsButton, searchDrugButton, purchaseDrugButton, purchaseHistoryButton);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

