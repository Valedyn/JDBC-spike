package at.spengergasse.valedyn;

import at.spengergasse.valedyn.Exceptions.MissingAnnotationException;
import at.spengergasse.valedyn.Exceptions.NoFieldException;
import at.spengergasse.valedyn.model.Person_test;

public class Main {
    public static void main(String[] args) throws MissingAnnotationException, NoFieldException {
        Person_test personTest = new Person_test();
        SQLConverter<Person_test> sqlConverter = new SQLConverter<>(personTest);

    }
}
