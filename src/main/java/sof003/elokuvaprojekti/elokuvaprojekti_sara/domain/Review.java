package sof003.elokuvaprojekti.elokuvaprojekti_sara.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String comment;
   private double rating;
   private LocalDate date;

   @ManyToOne
   @JoinColumn(name = "movie_id")
   private Movie movie;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   public Review(Long id, String comment, double rating, LocalDate date, Movie movie, User user) {
    this.id = id;
    this.comment = comment;
    this.rating = rating;
    this.date = date;
    this.movie = movie;
    this.user = user;
}


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getComment() {
    return comment;
}

public void setComment(String comment) {
    this.comment = comment;
}

public double getRating() {
    return rating;
}

public void setRating(double rating) {
    this.rating = rating;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public Movie getMovie() {
    return movie;
}

public void setMovie(Movie movie) {
    this.movie = movie;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

}
