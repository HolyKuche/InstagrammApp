package org.kuchkickingyourass.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.kuchkickingyourass.service.InstagramService;
import org.kuchkickingyourass.InstagramApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameCtrl;

    @FXML
    private PasswordField passwordCtrl;

    public void logIn() {
        InstagramService service = InstagramService.getInstance();
        try {
            service.login(usernameCtrl.getText(), passwordCtrl.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        InstagramApp.initMainForm();
    }

    public void cancel() {
        System.exit(0);
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
