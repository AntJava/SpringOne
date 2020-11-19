package lesson5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import lesson5.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User findFirstByName(String name);
}
