package org.kuchkickingyourass.service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;

import java.io.IOException;

public final class LoginService extends Service<InstagramLoginResult> {

    private static LoginService service = null;
    private ControlService controlService = null;

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private LoginService() {}
    
    public static LoginService getInstance() {
        if (service == null) {
            service = new LoginService();
        }
        return service;
    }

    public ControlService getControlService() {
        return controlService;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    @Override
    public void start() {
        super.reset();
        super.start();
    }
    
    @Override
    protected Task<InstagramLoginResult> createTask() {
        return new Task<InstagramLoginResult>() {
            @Override
            protected InstagramLoginResult call() {
                Instagram4j instagram = Instagram4j.builder().username(username.get()).password(password.get()).build();
                instagram.setup();
                InstagramLoginResult result = null;
                try {
                    result = instagram.login();
                    updateMessage(result.getMessage());
                    if (result.getStatus().equals("ok")) {
                        controlService = new ControlService(instagram);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
        };
    }
}
