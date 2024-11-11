package sof003.elokuvaprojekti.elokuvaprojekti_sara;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.User;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.UserRepository;

public class UserDataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Luodaan käyttäjiä ja salataan salasanat
            if (userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("userpassword"));
                user.setRole("USER");
                userRepository.save(user);
            }

            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpassword"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}
