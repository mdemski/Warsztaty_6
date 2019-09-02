package pl.coderslab.workshop.twitter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.model.User;
import pl.coderslab.workshop.twitter.repositories.TweetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TweetService {

    private TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> findAllByUser(User user) {
        List<Tweet> tweetList = tweetRepository.findAllByUser(user);
        return tweetList;
    }

    public List<Tweet> findAllByUserEmail(String email) {
        List<Tweet> tweetList = tweetRepository.findAllByUserEmail(email);
        return tweetList;
    }

    public List<Tweet> findAllByCreatedGreaterThan(LocalDateTime dateTime) {
        List<Tweet> tweetList = tweetRepository.findAllByCreatedGreaterThan(dateTime);
        return tweetList;
    }

    public List<Tweet> findAllTweets(){
        List<Tweet> tweetList = tweetRepository.findAll();
        return tweetList;
    }
}
