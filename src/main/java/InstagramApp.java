import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.List;

public class InstagramApp {

    public static void main(String[] args) {
        InstagramService service = new InstagramService("", "");

        List<InstagramUserSummary> followers = service.getFollowers();
        List<InstagramUserSummary> followings = service.getFollowings();
        List<InstagramUserSummary> nonReciprocalFollowings = service.getNonReciprocalFollowings();

        System.out.println("Followers:");
        followers.forEach(user -> System.out.println(user.getUsername()));
        System.out.println("---------------------------------------");
        System.out.println("Followings:");
        followings.forEach(user -> System.out.println(user.getUsername()));
        System.out.println("---------------------------------------");
        System.out.println("Non-reciprocal followings:");
        nonReciprocalFollowings.forEach(user -> System.out.println(user.getUsername()));
    }
}
