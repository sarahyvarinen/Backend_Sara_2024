package sof003.elokuvaprojekti.elokuvaprojekti_sara.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.MovieRepository;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.Review;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.ReviewRepository;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository; // Lisää tämä

    // Kaikki arviot
    @GetMapping
    public String getAllReviews(Model model) {
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "reviews";  // Palauttaa "reviews.html" Thymeleaf-näkymän
    }

    @GetMapping("/add-review")
    public String showAddReviewForm(@RequestParam Long movieId, Model model) {
        Review review = new Review();
        model.addAttribute("review", review);
        model.addAttribute("movieId", movieId);
        model.addAttribute("movies", movieRepository.findAll()); // Hae kaikki elokuvat

        // Tulosta elokuvat konsoliin
        System.out.println("Movies fetched: " + movieRepository.findAll());
        
        return "add-review"; // Palauttaa add-review.html-näkymän
    }




    // Näytä lomake uuden arvion lisäämiseen
    @GetMapping("/add")
    public String showAddReviewForm(Model model) {
        model.addAttribute("review", new Review()); // Jos Review-luokassa on oletusmuodostaja
        return "add-review";  // Palauttaa "add-review.html" Thymeleaf-lomakenäkymän
    }

    // Uuden arvion lisääminen
   // @PostMapping("/add")
   // public String addReview(@ModelAttribute Review review) {
   //     reviewRepository.save(review);
   //     return "redirect:/reviews";  // Palauttaa arvion lisäyksen jälkeen listanäkymään
   // }

    // Arvioiden hakeminen elokuvan perusteella
    @GetMapping("/movie/{movieId}")
    public String getReviewsByMovie(@PathVariable Long movieId, Model model) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "reviews";  // Palauttaa arvion tietylle elokuvalle
    }

    // Arvioiden hakeminen käyttäjän perusteella
    @GetMapping("/user/{userId}")
    public String getReviewsByUser(@PathVariable Long userId, Model model) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        model.addAttribute("reviews", reviews);
        return "reviews";  // Palauttaa arvion tietylle käyttäjälle
    }

    // Arvion poistaminen
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
        return "redirect:/reviews";  // Palauttaa listanäkymään arvion poistamisen jälkeen
    }
}