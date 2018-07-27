package service;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public final class InstagramService {

    private static InstagramService service = null;
    private static Instagram4j instagram;
    private static InstagramUser user;

    private InstagramService() {}

    public static InstagramService getInstance() {
        if (service == null) {
            service = new InstagramService();
        }
        return service;
    }

    public void login(String username, String password) throws IOException {
        instagram = Instagram4j.builder().username(username).password(password).build();

        instagram.setup();
        instagram.login();

        user = getUser(username);
    }

    public InstagramUser getMe() {
        return user;
    }

    public InstagramUser getUser(String username) {
        InstagramSearchUsernameResult userResult = null;
        try {
            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userResult != null ? userResult.getUser() : null;
    }

    public List<InstagramUserSummary> getFollowers() throws IOException {
        return instagram
                .sendRequest(new InstagramGetUserFollowersRequest(user.getPk()))
                .getUsers();
    }

    public List<InstagramUserSummary> getFollowings() throws IOException {
        return instagram
                .sendRequest(new InstagramGetUserFollowingRequest(user.getPk()))
                .getUsers();
    }

    public List<InstagramUserSummary> getNonReciprocalFollowings() throws IOException {
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
