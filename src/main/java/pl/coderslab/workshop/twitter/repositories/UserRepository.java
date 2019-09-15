package pl.coderslab.workshop.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop.twitter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    long countByEmail(String email);

    User getByEmail(String email);

    User save(User user);
}
