package at.spengergasse.grueneis.dao;

import at.spengergasse.grueneis.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PersonDataAccessObject {

    public List<Person> findAll() {
        return Collections.emptyList();
    }

    public Optional<Person> save(Optional<Person> person) {
        return person;
    }
}
