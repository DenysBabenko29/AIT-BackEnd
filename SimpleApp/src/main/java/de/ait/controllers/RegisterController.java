package de.ait.controllers;

import de.ait.models.User;
import de.ait.servises.RegisterService;

import java.util.List;
import java.util.Scanner;

public class RegisterController {
    private final Scanner scanner;
    private final RegisterService registerService;

    public RegisterController(Scanner scanner, RegisterService registerService) {
        this.scanner = scanner;
        this.registerService = registerService;
    }

    public void register(){
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        User user = (User) registerService.addUser(email, password);
        System.out.println(user);
    }
    public void getAllUsers(){
        List<User> users = registerService.getAllUsers();
        System.out.println(users);
    }
}
