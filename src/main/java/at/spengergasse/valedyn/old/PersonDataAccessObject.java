package at.spengergasse.valedyn.old;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// old version
public class PersonDataAccessObject {
    public static void main(String[] args) {
        PersonDataAccessObject personDataAccessObject = new PersonDataAccessObject();
        personDataAccessObject.save(Optional.of(new Person("Max", "Mustermann", LocalDate.now())));
        System.out.println(personDataAccessObject.findAll());
        personDataAccessObject.close();
    }
    private final Connection connection;
    private final String tableName = "people";
    private final String url = "jdbc:h2:mem:jdbc-spike";
    private final String username = "sa";
    private final String password = "";
    public PersonDataAccessObject()  {
        try {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(url, username, password);

                Statement statement = connection.createStatement();

                String createTableSQL = "CREATE TABLE people ( " +
                        "firstName VARCHAR(50) NOT NULL, " +
                        "lastName VARCHAR(50) NOT NULL, " +
                        "birthdate DATE NOT NULL " +
                        ");";

                statement.executeUpdate(createTableSQL);

                statement.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }


    }
    public void close() {
        try {
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            while (resultSet.next()) {

                Person person = new Person(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("birthdate").toLocalDate());
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // create statement
        // bind input arguments into statement (optional)
        // execute statement -> pointer to rows
        // loop for rows
        //   create an object for every row and map data

        return persons;
    }
    public Optional<Person> save(Optional<Person> person) {
        if (person.isPresent()) {
            Person realPerson = person.get();
            List<Person> personList = findAll();
            boolean existsInDatabase = personList.contains(realPerson);

            try {
                if (existsInDatabase) {
                    // Person already exists in the database, perform an UPDATE
                    String updateQuery = "UPDATE " + tableName + " SET firstName=?, lastName=?, birthdate=? WHERE firstName=?";
                    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                        statement.setString(1, realPerson.firstName());
                        statement.setString(2, realPerson.lastName());
                        statement.setDate(3, Date.valueOf(realPerson.birthdate()));
                        statement.setString(4, realPerson.firstName());
                    }
                } else {
                    String insertQuery = "INSERT INTO " + tableName + " (firstName, lastName, birthdate) VALUES (?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                        statement.setString(1, realPerson.firstName());
                        statement.setString(2, realPerson.lastName());
                        statement.setDate(3, Date.valueOf(realPerson.birthdate()));

                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Person inserted successfully.");
                        } else {
                            System.out.println("Person insertion failed.");
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return Optional.of(realPerson);
        } else {
            return Optional.empty();
        }
        // decide if INSERT or UPDATE
        // create statement
        // bind input arguments
        // execute statement
        // if INSERT -> fetch primary key
        }



}
