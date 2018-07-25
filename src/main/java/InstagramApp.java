import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InstagramApp {

    private static String username;
    private static String password;

    public static void main(String[] args) throws IOException {
        disableLogger();

        InstagramService service = InstagramService.getInstance();

        readLoginInfo();
        System.out.println("Ждити...");
        System.out.println();
        service.login(username, password);

        for (String arg: Arrays.asList(args)) {
            executeCommand(arg);
        }
        System.out.println("Press Enter to exit.");
        System.in.read();
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

    private static void readLoginInfo() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("logininfo.txt"))) {
            username = bufferedReader.readLine();
            password = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void disableLogger() {
        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }
    }
}
