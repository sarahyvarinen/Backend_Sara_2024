package sof003.elokuvaprojekti.elokuvaprojekti_sara.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.MovieRepository;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.Movie;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    // Näyttää lomakkeen elokuvan lisäämistä varten
    @GetMapping("/add-movie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie"; // Varmista, että tämä on oikea näkymän nimi
    }

    // Käsittelee elokuvan lisäämistä Thymeleaf-lomakkeen kautta
    @PostMapping("/add-movie")
    public String addMovieFromForm(Movie movie) {
        movieRepository.save(movie);
        return "redirect:/movies"; // Suuntaa takaisin elokuvien listalle
    }

    // Näyttää kaikki elokuvat
    @GetMapping
    public String showMoviesList(Model model) {
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies"; // Tämä on Thymeleaf-sivu, joka näyttää elokuvat
    }

    // Elokuvan poistaminen
    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return "redirect:/movies"; // Ohjaa takaisin elokuvien listalle
    }
}

