package at.spengergasse.valedyn.dao;

import at.spengergasse.valedyn.model.Entity;

import java.sql.Connection;
import java.util.Optional;

public class DAO<T extends Entity> implements DAOInterface<T> {
    private Connection connection;
    public DAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Optional<T> get(int id) {
      //  connection.prepareStatement();
        return Optional.empty();
    }

    @Override
    public void update(T updateObject, String[] params) {

    }

    @Override
    public void remove(T removeObject) {

    }

    @Override
    public void save(T saveObject) {

    }
}
