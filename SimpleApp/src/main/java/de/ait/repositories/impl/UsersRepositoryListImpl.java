package de.ait.repositories.impl;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryListImpl implements UsersRepository {
    private final List<User> usersRepository = new ArrayList<>();
    private Long userID = 1L;


    @Override
    public User save(User user) {
        if(user != null){
            usersRepository.add(user);
            user.setId(userID++);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return usersRepository;
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public User update(String email, String password) {
        return null;
    }

    @Override
    public User deleteByID(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
