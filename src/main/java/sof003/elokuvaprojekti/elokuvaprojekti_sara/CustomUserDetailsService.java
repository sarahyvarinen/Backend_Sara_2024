package sof003.elokuvaprojekti.elokuvaprojekti_sara;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().split(",")) // Oletetaan, että roolit on tallennettu pilkulla eroteltuna
                .build();
    }
}
