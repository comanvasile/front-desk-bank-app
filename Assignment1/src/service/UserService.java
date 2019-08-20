package service;

import model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(Long id);

    User login(String username, String password);

    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);
}
