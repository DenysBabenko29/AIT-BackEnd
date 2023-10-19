package de.ait.repositories;

import java.util.List;

public interface CrudRepository<T> {
    T save(T model);
    List<T> findAll();
    T findByID(Long id);
    T update(String email, String password);
    T deleteByID(Long id);
}
