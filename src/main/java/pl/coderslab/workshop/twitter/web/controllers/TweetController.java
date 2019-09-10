package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.workshop.twitter.dto.AddCommentDTO;
import pl.coderslab.workshop.twitter.dto.AddTweetDTO;
import pl.coderslab.workshop.twitter.dto.CommentDTO;
import pl.coderslab.workshop.twitter.dto.TweetDTO;
import pl.coderslab.workshop.twitter.services.CommentService;
import pl.coderslab.workshop.twitter.services.TweetService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;
    private CommentService commentService;

    public TweetController(TweetService tweetService, CommentService commentService) {
        this.tweetService = tweetService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public String prepareWebAboutTweet(@PathVariable Long id, Model model){
        TweetDTO tweetDTO = tweetService.findOneByTweetId(id);
        List<CommentDTO> commentList = commentService.getCommentByTweetId(id);
        model.addAttribute("tweet", tweetDTO);
        model.addAttribute("comments", commentList);
        model.addAttribute("commentDTO", new AddCommentDTO());
        return "aboutTweet";
    }

    @PostMapping("/add-comment/{id}")
    public String processAddingCommentToTweet(@ModelAttribute("commentDTO") @Valid AddCommentDTO addCommentDTO, BindingResult result, @PathVariable Long id, Principal principal){
        if (result.hasErrors()){
            return "aboutTweet";
        } else {
            commentService.addCommentByUser(principal.getName(), addCommentDTO, id);
            return "redirect:/tweet/"+ id;
        }
    }
}
