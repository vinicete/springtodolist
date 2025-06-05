package fipp.revisao2bim.repositories;

import fipp.revisao2bim.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
