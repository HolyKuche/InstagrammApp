package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.User;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import service.InstagramService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FollowersOverviewController implements Initializable {

    @FXML
    private ListView<User> userList;

    private final ObservableList<User> data = FXCollections.observableArrayList();

    public void updateData() {
        InstagramService service = InstagramService.getInstance();

        try {
            List<InstagramUserSummary> instagramUsers = service.getNonReciprocalFollowings();
            data.clear();
            data.addAll(instagramUsers.stream().map(User::new).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateData();
        userList.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<User>() {
                    @Override
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);
                        Optional.ofNullable(user).ifPresent(u -> {
                            Image img = new Image(u.getProfilePicUrl());
                            ImageView imgView = new ImageView(img);
                            setGraphic(imgView);
                            setText(u.getUsername());
                        });
                    }
                };
            }
        });
        userList.setItems(data);
    }
}
