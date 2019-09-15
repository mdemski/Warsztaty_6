package pl.coderslab.workshop.twitter.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshop.twitter.dto.SendMessageDTO;
import pl.coderslab.workshop.twitter.model.User;
import pl.coderslab.workshop.twitter.services.MessageService;
import pl.coderslab.workshop.twitter.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public String prepareMessageSender(Model model, Principal principal){
        String userName = principal.getName();
        model.addAttribute("messageDTO", new SendMessageDTO());
        return "message-sender";
    }

    @PostMapping("/posted")
    public String processMessageSender(@ModelAttribute("messageDTO") @Valid SendMessageDTO sendMessageDTO, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "message-sender";
        }
        if (sendMessageDTO.getRecipientName().equals(principal.getName())){
            //TODO dodać wyskakujące okienko, że nie można wysłać do siebie
            return "message-sender";
        }
        User recipient = userService.getUserByEmail(sendMessageDTO.getRecipientName());
        if (recipient == null){
            return "message-sender";
        }
//        messageService.
        return "redirect:/all-user-info";
    }
}
