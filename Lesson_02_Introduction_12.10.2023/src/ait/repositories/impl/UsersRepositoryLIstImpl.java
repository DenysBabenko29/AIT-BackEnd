package ait.repositories.impl;

import ait.model.User;
import ait.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositoryLIstImpl implements UsersRepository {
    private final List<User> usersRepository = new ArrayList<>();
    private Long id = 1L;

    @Override
    public void save(User user) {
            usersRepository.add(user);
            user.setId(id++);
    }

    @Override
    public User findByID(Long id) {
        if(id <= 0){
            throw new IllegalArgumentException("Поле id не может быть меньше или равно 0");
        }
        return usersRepository.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(usersRepository);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByName(String name) {
        return usersRepository.stream()
                .filter(user -> user.getEmail().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAllByName(String name) {
        return usersRepository.stream()
                .filter(user -> user.getEmail().equals(name))
                .collect(Collectors.toList());
    }
}
