package ait.servises;

import java.util.List;

public interface UsersService<T> {
    T create(String name, String email);
    List<T> findAllByName(String name);
    List<T> findAll();

}
