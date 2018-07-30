package org.kuchkickingyourass.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public class User {
    private final LongProperty pk;
    private final StringProperty username;
    private final StringProperty fullName;
    private final StringProperty profilePicUrl;

    public User(Long pk,
                String username,
                String fullName,
                String profilePicUrl) {
        this.pk = new SimpleLongProperty(pk);
        this.username = new SimpleStringProperty(username);
        this.fullName = new SimpleStringProperty(fullName);
        this.profilePicUrl = new SimpleStringProperty(profilePicUrl);
    }

    public User(InstagramUserSummary instagramUser) {
        this(instagramUser.getPk(),
                instagramUser.getUsername(),
                instagramUser.getFull_name(),
                instagramUser.getProfile_pic_url());
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
}
