package pl.coderslab.workshop.twitter.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.workshop.twitter.dto.RegistrationFormDTO;
import pl.coderslab.workshop.twitter.model.User;
import pl.coderslab.workshop.twitter.repositories.UserRepository;

@Service
@Transactional
public class RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isEmailAvailable(String email) {
        long countEmail = userRepository.countByEmail(email);
        if (countEmail > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void registerUser(RegistrationFormDTO data) {
        User user = new User();
        user.setEmail(data.getEmail());
        String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        userRepository.save(user);
    }
}
