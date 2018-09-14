package org.kuchkickingyourass.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.kuchkickingyourass.control.FollowButton;
import org.kuchkickingyourass.model.User;
import org.kuchkickingyourass.service.ControlService;
import org.kuchkickingyourass.service.LoginService;

import java.io.IOException;

public class UserWidgetController {

    private ControlService service;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private ImageView profilePicImg;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private FollowButton followButton;

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
        service = LoginService.getInstance().getControlService();
        profilePicImg.setImage(new Image(user.getProfilePicUrl()));
        usernameLabel.setText(user.getUsername());
        fullNameLabel.setText(user.getFullName());
        followButton.setOnFollow(getFollowHandler(user));
        followButton.setOnUnfollow(getUnfollowHandler(user));
        followButton.setIsFollowingMode(!user.getFollowing());
    }

    private EventHandler<ActionEvent> getFollowHandler(User user) {
        return event -> {
            try {
                service.follow(user.getPk());
                user.setFollowing(true);
                followButton.switchMode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> getUnfollowHandler(User user) {
        return event -> {
            try {
                service.unfollow(user.getPk());
                user.setFollowing(false);
                followButton.switchMode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
