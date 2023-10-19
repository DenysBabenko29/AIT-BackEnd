package ait;

import ait.controllers.RegisterController;
import ait.model.User;
import ait.repositories.CrudRepository;
import ait.repositories.UsersRepository;
import ait.repositories.impl.UsersRepositoryFileImpl;
import ait.repositories.impl.UsersRepositoryLIstImpl;
import ait.servises.UsersService;
import ait.servises.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository repositoryList = new UsersRepositoryLIstImpl();
        UsersRepository repositoryFile = new UsersRepositoryFileImpl("users.txt");
        UsersService<User> usersService = new UserServiceImpl(repositoryFile);
        RegisterController registerController = new RegisterController(scanner, usersService);

        while (true) {
            System.out.println("register - для добавления пользователя / exit - выход");
            String command = scanner.nextLine();
            if (command.equals("register")){
                registerController.register();
            }
            if (command.equals("exit")){
                break;
            }

        }

    }
}
