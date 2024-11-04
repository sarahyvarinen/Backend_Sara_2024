package sof003.elokuvaprojekti.elokuvaprojekti_sara.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Tarvittaessa kustomoituja hakuja, esim. arvosteluja elokuvan ID:n perusteella
    List<Review> findByMovieId(Long movieId);

    List<Review> findByUserId(Long userId);
}
