package com.example.pharmacymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane login_mainForm;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;


    public void loginBtn() throws IOException {

        //Linking the dashboard
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();

        loginBtn.getScene().getWindow().hide(); // this hides the login window

    }

    public void closeBtn(){
        System.exit(0);
    }

}
