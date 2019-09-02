package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshop.twitter.model.Tweet;
import pl.coderslab.workshop.twitter.services.TweetService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    TweetService tweetService;

    public HomePageController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public String prepareHomePage(Model model, Principal principal){
        String username = principal.getName();
        List<Tweet> tweets = tweetService.findAllTweets();
        model.addAttribute("tweets", tweets);
        model.addAttribute("username", username);
        return "index";
    }
}
