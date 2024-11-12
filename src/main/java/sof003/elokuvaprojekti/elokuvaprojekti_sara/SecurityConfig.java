package sof003.elokuvaprojekti.elokuvaprojekti_sara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // Tämä sallii @PreAuthorize käytön
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/movies/add", "/movies/delete/**").hasRole("ADMIN")  // Vain admin voi lisätä ja poistaa elokuvia
                    .requestMatchers("/movies").hasAnyRole("ADMIN", "USER")  // Admin ja User voivat nähdä elokuvia
                    .anyRequest().authenticated()  // Kaikkien muiden pyynnöjen täytyy olla autentikoituja
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")  // Määritellään kirjautumissivun URL
                    .permitAll()          // Sallitaan kaikille pääsy
                    .defaultSuccessUrl("/movies", true)  // Ohjaa elokuvien listalle kirjautumisen jälkeen
            )
            .logout(logout ->
                logout
                    .permitAll()          // Sallitaan kaikille pääsy uloskirjautumiseen
            );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
