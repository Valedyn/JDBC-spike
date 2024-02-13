package at.spengergasse.valedyn.dao;

import at.spengergasse.valedyn.model.Entity;

import java.util.Optional;

public interface DAOInterface<T extends Entity> {
    //crud operations
    // data access object = dao

    Optional<T> get(int id);
    void update(T updateObject, String[] params);
    void remove(T removeObject);
    void save(T saveObject);
}
