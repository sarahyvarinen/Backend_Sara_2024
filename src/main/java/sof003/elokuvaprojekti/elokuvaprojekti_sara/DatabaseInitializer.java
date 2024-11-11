package sof003.elokuvaprojekti.elokuvaprojekti_sara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.User;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tarkista, että käyttäjiä ei ole vielä luotu
        if (userRepository.count() == 0) {
            User user = new User("käyttäjä", new BCryptPasswordEncoder().encode("käyttäjänsalasana"), "USER");
            User admin = new User("admin", new BCryptPasswordEncoder().encode("adminsalasana"), "ADMIN");
            userRepository.save(user);
            userRepository.save(admin);
        }
    }
}
