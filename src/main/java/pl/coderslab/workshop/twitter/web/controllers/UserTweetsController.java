package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshop.twitter.services.TweetService;
import pl.coderslab.workshop.twitter.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user-tweets")
public class UserTweetsController {

    private TweetService tweetService;
    private UserService userService;

    public UserTweetsController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String prepareAllTweetsByUser(Principal principal, Model model) {
        String userName = principal.getName();
        Long userId = userService.getUserByEmail(userName).getId();
        model.addAttribute("tweets", tweetService.findAllByUserId(userId));
        return "all-user-tweets";
    }

}
