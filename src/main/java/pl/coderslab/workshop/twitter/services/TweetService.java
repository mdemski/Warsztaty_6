package pl.coderslab.workshop.twitter.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.workshop.twitter.dto.TweetDTO;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Comment;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.repositories.TweetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TweetService {

    private TweetRepository<AbstractEntity, Number> tweetRepository;

    public TweetService(TweetRepository<AbstractEntity, Number> tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<TweetDTO> findAllByUserId(Long userId) {
        Page<Tweet> tweets = tweetRepository.findAllByUserId(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), userId);
        return getTweetDTOS(tweets);
    }

    private List<TweetDTO> getTweetDTOS(Page<Tweet> tweets) {
        List<Tweet> content = tweets.getContent();
        return content.stream().map(source -> {
            TweetDTO dto = new TweetDTO();
            dto.setId(source.getId());
            dto.setText(source.getText());
            dto.setCreated(source.getCreated());
            dto.setUserName(source.getUser().getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<TweetDTO> findAllByCreatedGreaterThan(LocalDateTime dateTime) {
        Page<Tweet> tweetList = tweetRepository.findAllByCreatedGreaterThan(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), dateTime);
        return getTweetDTOS(tweetList);
    }

    public List<TweetDTO> findAllTweets() {
        Page<Tweet> tweets = tweetRepository.findAll(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")));
        return getTweetDTOS(tweets);
    }

    List<Comment> findAllByTweet(Tweet tweet) {
        List<Comment> comments = findAllByTweet(tweet);
        return comments;
    }

    public TweetDTO findOneByTweetId(Long id) {
        Tweet tweet = tweetRepository.findOneById(id);
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(id);
        tweetDTO.setUserName(tweet.getUser().getEmail());
        tweetDTO.setText(tweet.getText());
        tweetDTO.setCreated(tweet.getCreated());
        return tweetDTO;
    }
}
