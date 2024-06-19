package org.pharmacy.pharmacymanagementsystem.auth;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.dao.UserDAO;
import org.pharmacy.pharmacymanagementsystem.ui.WelcomeScreenFrame;

import java.sql.SQLException;

public class LoginFrame extends Application {

    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System - Login");

        // Background Image
        Image backgroundImage = new Image("file:src/main/resources/img/Pharmacy.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(400);
        backgroundImageView.setFitHeight(400);

        StackPane root = new StackPane();

        // Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Title
        Label title = new Label("Login to your account");
        title.setFont(Font.font("Arial", 20));
        title.setTextFill(Color.BLACK);
        grid.add(title, 0, 0, 2, 1);

        // Username
        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Color.BLACK);
        grid.add(usernameLabel, 0, 1);

        usernameField = new TextField();
        grid.add(usernameField, 1, 1);

        // Password
        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.BLACK);
        grid.add(passwordLabel, 0, 2);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        // Login Button
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            UserDAO userDAO = new UserDAO();

            try {
                if (userDAO.validateUser(username, password)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");
                    alert.showAndWait();
                    WelcomeScreenFrame welcomeScreen = new WelcomeScreenFrame();
                    welcomeScreen.start(new Stage());
                    primaryStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Database error.");
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(backgroundImageView, grid);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

