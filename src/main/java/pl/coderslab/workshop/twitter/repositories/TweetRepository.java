package pl.coderslab.workshop.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByUser(User user);

    List<Tweet> findAllByUserEmail(String email);

    List<Tweet> findAllByCreatedGreaterThan(LocalDateTime dateTime);

}
