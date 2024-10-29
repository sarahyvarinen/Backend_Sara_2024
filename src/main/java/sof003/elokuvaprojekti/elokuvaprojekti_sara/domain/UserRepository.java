
package sof003.elokuvaprojekti.elokuvaprojekti_sara.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sof003.elokuvaprojekti.elokuvaprojekti_sara.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Tarvittaessa voit lisätä kustomoituja metodeja, esim. hakemaan käyttäjää käyttäjänimellä
    Optional<User> findByUsername(String username);
}
