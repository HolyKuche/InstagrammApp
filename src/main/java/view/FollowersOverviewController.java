package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class FollowersOverviewController implements Initializable {

    @FXML
    private ListView<User> userList;

    private void updateUserList() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
