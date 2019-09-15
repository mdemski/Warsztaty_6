package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshop.twitter.services.MessageService;
import pl.coderslab.workshop.twitter.services.TweetService;
import pl.coderslab.workshop.twitter.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private TweetService tweetService;
    private UserService userService;
    private MessageService messageService;

    public UserController(TweetService tweetService, UserService userService, MessageService messageService) {
        this.tweetService = tweetService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String prepareAllInfoByUser(Principal principal, Model model) {
        String userName = principal.getName();
        Long userId = userService.getUserByEmail(userName).getId();
        model.addAttribute("tweets", tweetService.findAllByUserId(userId));
        model.addAttribute("received", messageService.findAllByUserIdReceived(userId));
        model.addAttribute("posted", messageService.findAllByUserIdPosted(userId));
        return "all-user-info";
    }

}
