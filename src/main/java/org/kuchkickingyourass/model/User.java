package org.kuchkickingyourass.model;

import javafx.beans.property.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public class User {
    private final LongProperty pk;
    private final StringProperty username;
    private final StringProperty fullName;
    private final StringProperty profilePicUrl;
    private final BooleanProperty following;

    public User(Long pk,
                String username,
                String fullName,
                String profilePicUrl,
                Boolean following) {
        this.pk = new SimpleLongProperty(pk);
        this.username = new SimpleStringProperty(username);
        this.fullName = new SimpleStringProperty(fullName);
        this.profilePicUrl = new SimpleStringProperty(profilePicUrl);
        this.following = new SimpleBooleanProperty(following);
    }

    public User(InstagramUserSummary instagramUser) {
        this(instagramUser.getPk(),
                instagramUser.getUsername(),
                instagramUser.getFull_name(),
                instagramUser.getProfile_pic_url(),
                true);
    }

    public long getPk() {
        return pk.get();
    }

    public LongProperty pkProperty() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk.set(pk);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getProfilePicUrl() {
        return profilePicUrl.get();
    }

    public StringProperty profilePicUrlProperty() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl.set(profilePicUrl);
    }

    public boolean getFollowing() {
        return following.get();
    }

    public BooleanProperty followingProperty() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following.set(following);
    }
}
