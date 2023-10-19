package de.ait;

import de.ait.controllers.RegisterController;
import de.ait.repositories.UsersRepository;
import de.ait.repositories.impl.UsersRepositoryFileImpl;
import de.ait.repositories.impl.UsersRepositoryListImpl;
import de.ait.servises.RegistrationServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepositoryList = new UsersRepositoryListImpl();
        UsersRepository usersRepositoryFile = new UsersRepositoryFileImpl("users.txt");
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl(usersRepositoryFile);
        RegisterController registerController = new RegisterController(scanner, registrationService);


        while(true){
            String command = scanner.nextLine();
            if(command.equals("addUser")){
                registerController.register();
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("users")) {
                registerController.getAllUsers();
            }
        }

    }
}
