package org.kuchkickingyourass.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kuchkickingyourass.InstagramApp;
import org.kuchkickingyourass.service.LoginService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginService service;

    @FXML
    private TextField usernameCtrl;

    @FXML
    private PasswordField passwordCtrl;

    @FXML
    private Label errorLabel;

    @FXML
    private ProgressIndicator spinner;

    @FXML
    private Button logInButton;

    public void logIn() {
        service.start();
    }

    public void exit() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = LoginService.getInstance();
        service.setOnRunning(event -> {
            logInButton.setDisable(true);
            spinner.setVisible(true);
            errorLabel.setVisible(false);
        });
        service.setOnSucceeded(event -> {
            logInButton.setDisable(false);
            spinner.setVisible(false);
            if (service.getValue().getStatus().equals("fail")) {
                errorLabel.setVisible(true);
            } else {
                InstagramApp.initMainForm();
            }
        });
        service.usernameProperty().bindBidirectional(usernameCtrl.textProperty());
        service.passwordProperty().bindBidirectional(passwordCtrl.textProperty());
        service.messageProperty().addListener((observable, oldValue, newValue) -> {
            errorLabel.textProperty().setValue(newValue);
        });
        readLoginInfo();
    }

    private void readLoginInfo() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("logininfo.txt"))) {
            usernameCtrl.setText(bufferedReader.readLine());
            passwordCtrl.setText(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
