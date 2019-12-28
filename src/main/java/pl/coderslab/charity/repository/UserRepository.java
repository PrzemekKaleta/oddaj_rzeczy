package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.username = :username")
    User findUserByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

}
