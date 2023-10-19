package ait.servises.impl;

import ait.model.User;
import ait.repositories.CrudRepository;
import ait.servises.UsersService;

import java.util.List;

public class UserServiceImpl implements UsersService<User> {
    private final CrudRepository<User> usersRepository;

    public UserServiceImpl(CrudRepository<User> usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User create(String name, String email) {

        check(name, email);
        checkUserEmail(email);

        User user = new User(name, email);
        usersRepository.save(user);

        return user;
    }

    @Override
    public List<User> findAllByName(String name) {
        if (name == null || name.equals("") || name.equals(" ")) {
            throw new IllegalArgumentException("Поле name не может быть пустым!");
        }
        return usersRepository.findAllByName(name);
    }


    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    private void check(String name, String email) {
        if (name == null || name.equals("") || name.equals(" ")) {
            throw new IllegalArgumentException("Поле name не может быть пустым!");
        }
        if (email == null || email.equals("") || email.equals(" ")) {
            throw new IllegalArgumentException("Поле Email не может быть пустым!");
        } else if (!email.contains("@")) {
            throw new IllegalArgumentException("Поле Email должно содержать '@'");
        }
    }

    private void checkUserEmail(String email) {
        User checkUserEmail = usersRepository.findByEmail(email);
        if (checkUserEmail != null) {
            throw new IllegalArgumentException("Такой пользователь уже существует");
        }
    }
}
