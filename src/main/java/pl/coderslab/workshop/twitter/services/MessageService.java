package pl.coderslab.workshop.twitter.services;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.coderslab.workshop.twitter.dto.MessageDTO;
import pl.coderslab.workshop.twitter.dto.SendMessageDTO;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Message;
import pl.coderslab.workshop.twitter.model.User;
import pl.coderslab.workshop.twitter.repositories.MessageRepository;
import pl.coderslab.workshop.twitter.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    private MessageRepository<AbstractEntity, Number> messageRepository;
    private UserRepository userRepository;

    public MessageService(MessageRepository<AbstractEntity, Number> messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<MessageDTO> findAllByUserIdReceived(Long id) {
        Page<Message> messageList = messageRepository.findAllByUserRecipient(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), id);
        return getMessageDTOS(messageList);
    }

    public List<MessageDTO> findAllByUserIdPosted(Long id) {
        Page<Message> messageList = messageRepository.findAllByUserSender(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), id);
        return getMessageDTOS(messageList);
    }

    public List<MessageDTO> findAllByCreated(LocalDateTime created) {
        Page<Message> messageList = messageRepository.findAllByCreated(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), created);
        return getMessageDTOS(messageList);
    }

    public MessageDTO findOneByTitle(String title) {
        Message messageRepo = messageRepository.findOneByTitle(title);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(messageRepo.getId());
        messageDTO.setSenderName(messageRepo.getSender().getEmail());
        messageDTO.setRecipientName(messageRepo.getRecipient().getEmail());
        messageDTO.setTitle(messageRepo.getTitle());
        messageDTO.setContent(messageRepo.getContent());
        messageDTO.setCreated(messageRepo.getPost());
//        messageDTO.setRead(messageRepo.isRead());
        return messageDTO;
    }

    public List<MessageDTO> findAllByRead(boolean read) {
        Page<Message> messageList = messageRepository.findAllByRead(new PageRequest(0, 20, new Sort(Sort.Direction.DESC, "created")), read);
        return getMessageDTOS(messageList);
    }

    private List<MessageDTO> getMessageDTOS(Page<Message> messageList) {
        List<Message> messages = messageList.getContent();
        return messages.stream().map(source -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(source.getId());
            messageDTO.setSenderName(source.getSender().getEmail());
            messageDTO.setRecipientName(source.getRecipient().getEmail());
            messageDTO.setTitle(source.getTitle());
            messageDTO.setContent(source.getContent());
            messageDTO.setCreated(source.getPost());
//            messageDTO.setRead(source.isRead());
            return messageDTO;
        }).collect(Collectors.toList());
    }

    private void sendMessage(SendMessageDTO sendMessageDTO, Principal principal) {
        Message message = new Message();
        User sender = new User();
        userRepository.save(sender);
        User recipient = new User();
//        recipient.setReceivedMessages(Lists.newArrayList(message));
        userRepository.save(recipient);
        message.setSender(userRepository.getByEmail(principal.getName()));
//        message.setRecipient(sendMessageDTO.getRecipientName());
    }

}
