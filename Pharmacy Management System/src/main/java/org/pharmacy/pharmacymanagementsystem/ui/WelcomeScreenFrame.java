package org.pharmacy.pharmacymanagementsystem.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScreenFrame extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome");

        // Background Image
        Image backgroundImage = new Image("file:src/main/resources/img/Pharmacy.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);

        StackPane root = new StackPane();

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(50));
        vbox.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to the Pharmacy Management System");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

        Button proceedButton = new Button("Proceed");
        proceedButton.setStyle("-fx-font-size: 16px;");

        proceedButton.setOnAction(e -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.start(new Stage());
            primaryStage.close();
        });

        vbox.getChildren().addAll(welcomeLabel, proceedButton);
        root.getChildren().addAll(backgroundImageView, vbox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

