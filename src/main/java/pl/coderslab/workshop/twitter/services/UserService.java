package pl.coderslab.workshop.twitter.services;

import org.springframework.stereotype.Service;
import pl.coderslab.workshop.twitter.dto.AddTweetDTO;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.model.User;
import pl.coderslab.workshop.twitter.repositories.TweetRepository;
import pl.coderslab.workshop.twitter.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private TweetRepository<AbstractEntity, Number> tweetRepository;

    public UserService(UserRepository userRepository, TweetRepository<AbstractEntity, Number> tweetRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public void addTweetByUser(String email, AddTweetDTO addTweetDTO) {
        Tweet tweet = new Tweet();
        tweet.setText(addTweetDTO.getText());
        tweet.setCreated(LocalDateTime.now());
        User user = userRepository.getByEmail(email);
        tweet.setUser(user);
        tweetRepository.save(tweet);
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
