import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class InstagramApp extends Application {

    private static String username;
    private static String password;

    private Stage primaryStage;
    private Pane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        if (readLoginInfo()) {
            initMainForm();
        } else {
            initLoginForm();
        }
    }

    private void initLoginForm() {
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

    public void initMainForm() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstagramApp.class.getResource("view/FollowersOverview.fxml"));
            rootLayout = loader.load();

            primaryStage.setTitle("Instagram App - " + username);
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

    private static void executeCommand(String command) throws IOException {
        InstagramService service = InstagramService.getInstance();
        switch (command) {
            case "-followers":
                System.out.println("Followers:");
                printUserList(service.getFollowers());
                System.out.println("---------------");
                break;
            case "-followings":
                System.out.println("Followings:");
                printUserList(service.getFollowers());
                System.out.println("---------------");
                break;
            case "-non-reciprocal-followings":
                System.out.println("Non-reciprocal followings:");
                printUserList(service.getNonReciprocalFollowings());
                System.out.println("---------------");
                break;
        }
    }

    private static void printUserList(List<InstagramUserSummary> userList) {
        for (int i = 0; i < userList.size(); i++) {
            InstagramUserSummary user = userList.get(i);
            int index = i + 1;
            String indexStr = index < 100 ? index < 10 ? "  " + index : " " + index : "" + index;
            System.out.println(indexStr + ") " + user.getUsername());
        }
    }

    private static boolean readLoginInfo() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("logininfo.txt"))) {
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void disableLogger() {
        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }
    }
}
