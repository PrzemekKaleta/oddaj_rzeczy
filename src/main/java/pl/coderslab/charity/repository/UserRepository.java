package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.User;

public interface UserRepository extends JpaRepository<Long, User> {

    User findByEmail(String email);

}
