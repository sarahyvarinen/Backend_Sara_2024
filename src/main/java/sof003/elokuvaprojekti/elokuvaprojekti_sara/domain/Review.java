package sof003.elokuvaprojekti.elokuvaprojekti_sara.domain;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;  // Viittaa Movie-olioon

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Viittaa User-olioon

    private int rating;
    private String comment;

    // Oletusmuodostaja
    public Review() {
    }

    // Muodostaja, joka ottaa kaikki kentät huomioon
    public Review(Movie movie, User user, int rating, String comment) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    // Getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movie=" + movie.getId() + // Voit halutessasi näyttää lisää elokuvan tietoja
                ", user=" + (user != null ? user.getUsername() : "N/A") + // Käyttäjänimi, jos käyttäjä on saatavilla
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
