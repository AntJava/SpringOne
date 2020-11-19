package lesson5.service;

import lesson5.domain.Product;
import lesson5.domain.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lesson5.dao.UserDao;
import lesson5.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements lesson5.service.UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User auth(String username, String password) {
        if(username == null || username.isEmpty()){
            System.out.println("You are not authenticated");
            return null;
        }
        User user = userDao.findFirstByName(username);
        if(user == null){
            System.out.println("You are not authenticated");
            return null;
        }
        if(!Objects.equals(password, user.getPassword())){
            System.out.println("You are not authenticated");
            return null;
        }
        System.out.println("You are authenticated");
        return user;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void saveAndSet(User user) {
        user.setRole(Role.USER);
        userDao.save(user);
    }

    @Override
    public void updateRole(Integer id, Optional<Role> role) {
        User user = userDao.findById(id).orElse(null);
        Role newRole = role.orElse(user.getRole());
        user.setRole(newRole);
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findFirstByName(username);
    }
}
