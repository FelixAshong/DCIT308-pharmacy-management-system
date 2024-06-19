package org.pharmacy.pharmacymanagementsystem;

import javafx.application.Application;
import javafx.stage.Stage;
import org.pharmacy.pharmacymanagementsystem.auth.LoginFrame;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

