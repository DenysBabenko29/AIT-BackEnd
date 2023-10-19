package de.ait.repositories.impl;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositoryFileImpl implements UsersRepository {
    private final String fileName;
    private Long id = 1L;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public User save(User user) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(id);
            bufferedWriter.write(user.getId() + ";" + user.getEmail() + ";" + user.getPassword());
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Проблемы с записью в файл");
        }
        id++;
        return user;
    }

    @Override
    public List<User> findAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(";"))
                    .map(user -> new User(Long.parseLong(user[0]), user[1], user[2]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(";"))
                    .filter(line -> line[1].equals(email))
                    .findFirst()
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .orElse(null);
        } catch (IOException e) {
            throw new IllegalArgumentException("Проблема с чтением записи из файла");
        }
    }
}
