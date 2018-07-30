package org.kuchkickingyourass.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.kuchkickingyourass.control.FollowButton;
import org.kuchkickingyourass.model.User;
import org.kuchkickingyourass.service.InstagramService;

import java.io.IOException;

public class UserWidgetController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private ImageView profilePicImg;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label fullNameLabel;

    private FollowButton actionButton;

    public UserWidgetController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserWidget.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getView() {
        return rootNode;
    }

    public void init(User user) {
        profilePicImg.setImage(new Image(user.getProfilePicUrl()));
        usernameLabel.setText(user.getUsername());
        fullNameLabel.setText(user.getFullName());

        actionButton = new FollowButton(false) {
            {
                setLayoutX(223);
                setLayoutY(20);
                setMnemonicParsing(false);
                setOnFollow(event -> {
                    follow(user);
                    actionButton.switchMode();
                });
                setOnUnfollow(event -> {
                    unfollow(user);
                    actionButton.switchMode();
                });
                AnchorPane.setRightAnchor(this, 10.);
            }
        };
        rootNode.getChildren().add(actionButton);
    }

    private void follow(User user) {
        InstagramService service = InstagramService.getInstance();
        try {
            service.follow(user.getPk());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unfollow(User user) {
        InstagramService service = InstagramService.getInstance();
        try {
            service.unfollow(user.getPk());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
