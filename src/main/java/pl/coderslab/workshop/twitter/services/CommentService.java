package pl.coderslab.workshop.twitter.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.coderslab.workshop.twitter.dto.AddCommentDTO;
import pl.coderslab.workshop.twitter.dto.CommentDTO;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Comment;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.repositories.CommentRepository;
import pl.coderslab.workshop.twitter.repositories.TweetRepository;
import pl.coderslab.workshop.twitter.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private CommentRepository<AbstractEntity, Number> commentRepository;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository<AbstractEntity, Number> commentRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDTO> getCommentsByTweet(Tweet tweet) {
        Page<Comment> commentList = commentRepository.findAllByTweet(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), tweet);
        return getCommentDTOS(commentList);
    }

    private List<CommentDTO> getCommentDTOS(Page<Comment> commentList) {
        List<Comment> commentText = commentList.getContent();
        return commentText.stream().map(source -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setUserName(source.getUser().getEmail());
            commentDTO.setTweetName(source.getTweet().toString());
            commentDTO.setCreated(source.getCreated());
            commentDTO.setText(source.getText());
            return commentDTO;
        }).collect(Collectors.toList());
    }

    public List<CommentDTO> getCommentByTweetId(Long id) {
        Page<Comment> commentList = commentRepository.findAllByTweetId(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), id);
        return getCommentDTOS(commentList);
    }

    public void addCommentByUser(String email, AddCommentDTO addCommentDTO, Long id){
        Comment comment = new Comment();
        comment.setText(addCommentDTO.getComment());
        comment.setCreated(LocalDateTime.now());
        comment.setTweet(tweetRepository.findOneById(id));
        comment.setUser(userRepository.getByEmail(email));
        commentRepository.save(comment);
    }
}
