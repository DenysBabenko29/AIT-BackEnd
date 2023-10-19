package de.ait.servises;

import java.util.List;

public interface RegisterService<T> {
    T addUser(String email, String password);
    List<T> getAllUsers();
}
