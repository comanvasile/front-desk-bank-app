package service.impl;

import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            return userRepository.update(user);
        } else {
            return userRepository.create(user);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null)
            if (user.getPassword().equals(password))
                return user;
        return null;

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }


}
