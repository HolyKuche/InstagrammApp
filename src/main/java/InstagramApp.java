import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.IOException;
import java.util.List;

public class InstagramApp {
    public static void main(String[] args) {
        Instagram4j instagram = Instagram4j.builder().username("").password("").build();
        instagram.setup();
        try {
            instagram.login();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InstagramSearchUsernameResult userResult = null;
        try {
            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert userResult != null;
        System.out.println("ID for @ is " + userResult.getUser().getPk());
        System.out.println("Number of followers: " + userResult.getUser().getFollower_count());

        InstagramGetUserFollowersResult githubFollowers = null;
        try {
            githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<InstagramUserSummary> users = githubFollowers.getUsers();
        for (InstagramUserSummary user : users) {
            System.out.println("User " + user.getUsername() + " follows !");
        }
    }
}
