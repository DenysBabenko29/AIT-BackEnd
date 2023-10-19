package ait.repositories;

import java.util.List;

public interface CrudRepository<T> {

    void save(T user);
    T findByID(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void  update(T user);
    T findByEmail(String email);
    T findByName(String name);
    List<T> findAllByName(String name);
}
