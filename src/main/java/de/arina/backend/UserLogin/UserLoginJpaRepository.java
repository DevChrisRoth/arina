package de.arina.backend.UserLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginJpaRepository extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> checkIfEmailExists(String email);
    UserLogin findByEmail(String email);


}
