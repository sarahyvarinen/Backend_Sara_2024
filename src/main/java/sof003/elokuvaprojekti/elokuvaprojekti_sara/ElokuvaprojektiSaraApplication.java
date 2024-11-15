package sof003.elokuvaprojekti.elokuvaprojekti_sara;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.Movie;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.MovieRepository;

@SpringBootApplication
public class ElokuvaprojektiSaraApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElokuvaprojektiSaraApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(MovieRepository movieRepository) {
        return (args) -> {
            Movie exampleMovie = new Movie("Esimerkki Elokuva", "Draama", 2023, "Ohjaaja Esimerkki", 5);
            movieRepository.save(exampleMovie);
            System.out.println("Esimerkki elokuva tallennettu tietokantaan: " + exampleMovie);
        };
    }
}
