package de.ait.servises;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.util.List;

public class RegistrationServiceImpl implements RegisterService<User> {
    private final UsersRepository usersRepository;


    public RegistrationServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User addUser(String email, String password) {
        if(email == null || email.equals("") || email.equals(" ")){
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if(password == null || password.equals("") || password.equals(" ")){
            throw new IllegalArgumentException("Password не может быть пустым");
        }

        /*User checkEmail = usersRepository.findByEmail(email);

        if (checkEmail != null){
            throw new IllegalArgumentException("Такой email уже зарегестрирован");
        }
        */
        User user = new User(email, password);
        usersRepository.save(user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
