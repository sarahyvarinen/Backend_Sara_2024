package sof003.elokuvaprojekti.elokuvaprojekti_sara.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Table(name = "app_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public User(Long id, String username, String password, String role, List<Review> reviews) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
}
