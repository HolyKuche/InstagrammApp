package org.kuchkickingyourass.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class FollowButton extends Button {

    @FXML
    private boolean isFollowingMode;

    @FXML
    private EventHandler<ActionEvent> onFollow = null;

    @FXML
    private EventHandler<ActionEvent> onUnfollow = null;

    @FXML
    private String followText= "Follow";

    @FXML
    private String unfollowText = "Unfollow";

    public FollowButton() {
        this(true);
    }

    public FollowButton(boolean isFollowingMode) {
        super();
        setIsFollowingMode(isFollowingMode);
    }

    public void switchMode() {
        setIsFollowingMode(!isFollowingMode);
    }

    public boolean getIsFollowingMode() {
        return isFollowingMode;
    }

    public void setIsFollowingMode(boolean isFollowingMode) {
        this.isFollowingMode = isFollowingMode;
        if (isFollowingMode) {
            setOnAction(onFollow);
            setText(followText);
            setTextFill(Color.GREEN);
        } else {
            setOnAction(onUnfollow);
            setText(unfollowText);
            setTextFill(Color.RED);
        }
    }

    public EventHandler<ActionEvent> getOnFollow() {
        return onFollow;
    }

    public void setOnFollow(EventHandler<ActionEvent> onFollow) {
        this.onFollow = onFollow;
        if (isFollowingMode) {
            setOnAction(onFollow);
        }
    }

    public EventHandler<ActionEvent> getOnUnfollow() {
        return onUnfollow;
    }

    public void setOnUnfollow(EventHandler<ActionEvent> onUnfollow) {
        this.onUnfollow = onUnfollow;
        if (!isFollowingMode) {
            setOnAction(onUnfollow);
        }
    }

    public String getFollowText() {
        return followText;
    }

    public void setFollowText(String followText) {
        this.followText = followText;
        if (isFollowingMode) {
            setText(followText);
        }
    }

    public String getUnfollowText() {
        return unfollowText;
    }

    public void setUnfollowText(String unfollowText) {
        this.unfollowText = unfollowText;
        if (!isFollowingMode) {
            setText(unfollowText);
        }
    }
}