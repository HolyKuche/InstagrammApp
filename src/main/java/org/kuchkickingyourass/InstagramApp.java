package org.kuchkickingyourass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class InstagramApp extends Application {

    private static Stage primaryStage;
    private static Pane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        InstagramApp.primaryStage = primaryStage;
        initLoginForm();
    }

    public static void initLoginForm() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstagramApp.class.getResource("view/LogIn.fxml"));
            rootLayout = loader.load();

            primaryStage.setTitle("Authorization");
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void initMainForm() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstagramApp.class.getResource("view/FollowersOverview.fxml"));
            rootLayout = loader.load();

            primaryStage.setTitle("Unfollow all!!1");
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
