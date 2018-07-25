import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstagramService {

    private Instagram4j instagram;
    private InstagramSearchUsernameResult userResult;

    public InstagramService(String login, String password) {
        instagram = Instagram4j.builder().username(login).password(password).build();

        instagram.setup();
        try {
            instagram.login();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(login));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<InstagramUserSummary> getFollowers() {
        InstagramGetUserFollowersResult followersResult = null;
        try {
            followersResult = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return followersResult != null ? followersResult.getUsers() : new ArrayList<>();
    }

    public List<InstagramUserSummary> getFollowings() {
        InstagramGetUserFollowersResult followingsResult = null;
        try {
            followingsResult = instagram.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return followingsResult != null ? followingsResult.getUsers() : new ArrayList<>();
    }

    public List<InstagramUserSummary> getNonReciprocalFollowings() {
        List<Long> followersIds = getFollowers().stream().map(InstagramUserSummary::getPk).collect(Collectors.toList());
        List<InstagramUserSummary> followings = getFollowings();
        List<Long> nonReciprocalFollowingIds = followings.stream()
                .map(InstagramUserSummary::getPk)
                .filter(followingId -> !followersIds.contains(followingId))
                .collect(Collectors.toList());
        return followings.stream()
                .filter(user -> nonReciprocalFollowingIds.contains(user.getPk()))
                .collect(Collectors.toList());
    }
}
