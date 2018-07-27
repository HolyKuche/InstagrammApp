package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.InstagramService;

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
    }

    public void cancel() {
        System.exit(0);
    }

    private void openFollowersOverviewForm() {

    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
