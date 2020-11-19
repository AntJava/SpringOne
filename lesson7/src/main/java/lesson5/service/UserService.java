package lesson5.service;

import lesson5.domain.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import lesson5.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User getById(Integer id);
    User auth(String name, String password);

    List<User> getAll();
    void saveAndSet(User user);
    void updateRole(Integer id, Optional<Role> role);
}
