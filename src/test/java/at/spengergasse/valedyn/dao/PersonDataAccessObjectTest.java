package at.spengergasse.valedyn.dao;

import at.spengergasse.valedyn.old.Person;
import at.spengergasse.valedyn.old.PersonDataAccessObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonDataAccessObjectTest {

    private static Connection connection;

    @BeforeAll
    static void teachdownDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void setUpDatabaseConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:jdbc-spike", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findAll() {
        PersonDataAccessObject personDataAccessObject = new PersonDataAccessObject();

        List<Person> people = personDataAccessObject.findAll();

        assertThat(people).isNotNull();
    }
}
