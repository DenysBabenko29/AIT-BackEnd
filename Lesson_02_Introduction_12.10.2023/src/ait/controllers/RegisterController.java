package ait.controllers;

import ait.model.User;
import ait.servises.UsersService;

import java.util.Scanner;

public class RegisterController {
    private final Scanner scanner;
    private final UsersService<User> usersService;

    public RegisterController(Scanner scanner, UsersService<User> usersService) {
        this.scanner = scanner;
        this.usersService = usersService;
    }

    public void register(){
        System.out.println("Введите name");
        String name = scanner.nextLine();
        System.out.println("Введите email");
        String email = scanner.nextLine();

        usersService.create(name, email);
    }
}
