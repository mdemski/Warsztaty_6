package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshop.twitter.dto.AddTweetDTO;
import pl.coderslab.workshop.twitter.dto.TweetDTO;
import pl.coderslab.workshop.twitter.services.TweetService;
import pl.coderslab.workshop.twitter.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    private TweetService tweetService;
    private UserService userService;

    public HomePageController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping
    public String prepareHomePage(Model model, Principal principal){
        String username = principal.getName();
        List<TweetDTO> tweets = tweetService.findAllTweets();
        model.addAttribute("tweets", tweets);
        model.addAttribute("username", username);
        model.addAttribute("tweetDTO", new AddTweetDTO());
        return "index";
    }

    @PostMapping("/add-tweet")
    public String processAddingPost(@ModelAttribute("tweetDTO") @Valid AddTweetDTO tweetDTO, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "index";
        } else {
            userService.addTweetByUser(principal.getName(), tweetDTO);
            return "redirect:/";
        }
    }

}
