package pl.coderslab.workshop.twitter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Comment;
import pl.coderslab.workshop.twitter.model.Tweet;

public interface CommentRepository<T extends AbstractEntity, L extends Number> extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByTweet(Pageable pageable, Tweet tweet);

    Page<Comment> findAllByTweetId(Pageable pageable, Long id);
}
