package pl.coderslab.workshop.twitter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.model.User;

import java.time.LocalDateTime;

public interface TweetRepository<T extends AbstractEntity, L extends Number> extends JpaRepository<Tweet, Long> {

    Page<Tweet> findAllByUser(Pageable pageable, User user);

    Page<Tweet> findAllByUserId(Pageable pageable, Long userId);

    Page<Tweet> findAllByCreatedGreaterThan(Pageable pageable, LocalDateTime dateTime);

    Tweet findOneById(Long id);

}
