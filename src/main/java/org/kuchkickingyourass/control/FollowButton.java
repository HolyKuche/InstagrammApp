package org.kuchkickingyourass.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class FollowButton extends Button {

    private boolean isFollowMode;

    private EventHandler<ActionEvent> onFollow = null;

    private EventHandler<ActionEvent> onUnfollow = null;

    private String followText= "Follow";

    private String unfollowText = "Unfollow";

    public FollowButton() {
        this(true);
    }

    public FollowButton(boolean isFollowMode) {
        super();
        setIsFollowMode(isFollowMode);
    }

    public void switchMode() {
        setIsFollowMode(!isFollowMode);
    }

    public boolean getIsFollowMode() {
        return isFollowMode;
    }

    public void setIsFollowMode(boolean isFollowMode) {
        if (this.isFollowMode = isFollowMode) {
            setOnAction(onUnfollow);
            setText(unfollowText);
            setTextFill(Color.RED);
        } else {
            setOnAction(onFollow);
            setText(unfollowText);
            setTextFill(Color.RED);
        }
    }

    public EventHandler<ActionEvent> getOnFollow() {
        return onFollow;
    }

    public void setOnFollow(EventHandler<ActionEvent> onFollow) {
        this.onFollow = onFollow;
    }

    public EventHandler<ActionEvent> getOnUnfollow() {
        return onUnfollow;
    }

    public void setOnUnfollow(EventHandler<ActionEvent> onUnfollow) {
        this.onUnfollow = onUnfollow;
    }

    public String getFollowText() {
        return followText;
    }

    public void setFollowText(String followText) {
        this.followText = followText;
    }

    public String getUnfollowText() {
        return unfollowText;
    }

    public void setUnfollowText(String unfollowText) {
        this.unfollowText = unfollowText;
    }
}