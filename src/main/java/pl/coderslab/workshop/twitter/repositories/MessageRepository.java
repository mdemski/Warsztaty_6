package pl.coderslab.workshop.twitter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.twitter.model.AbstractEntity;
import pl.coderslab.workshop.twitter.model.Message;

import java.time.LocalDateTime;

public interface MessageRepository<T extends AbstractEntity, L extends Number> extends JpaRepository<Message, Long> {

    Page<Message> findAllByUserRecipient(Pageable pageable, Long id);

    Page<Message> findAllByUserSender(Pageable pageable, Long id);

    Page<Message> findAllByCreated(Pageable pageable, LocalDateTime created);

    Message findOneByTitle(String title);

    Page<Message> findAllByRead(Pageable pageable, boolean read);

}
