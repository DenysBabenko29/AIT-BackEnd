package ait.repositories.impl;

import ait.model.User;
import ait.repositories.UsersRepository;

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
    public void save(User user) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(id);
            writer.write(user.getId() + ";" + user.getName() + ";" + user.getEmail());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка записи в файл");
        }
        id++;
    }

    @Override
    public User findByID(Long id) {
        return findAll().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(";"))
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения из файла");
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findByEmail(String email) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(";"))
                    .filter(userLine -> userLine[2].equals(email))
                    .findFirst()
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2]))
                    .orElse(null);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения из файла");
        }

    }

    @Override
    public User findByName(String name) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAllByName(String name) {

        return findAll().stream()
                .filter(user -> user.getEmail().equals(name))
                .collect(Collectors.toList());
    }
}
