package at.spengergasse.valedyn.model;

import at.spengergasse.valedyn.annotations.AllColumns;
import at.spengergasse.valedyn.annotations.Column;
import at.spengergasse.valedyn.annotations.Table;

import java.time.LocalDate;

@Table(tableName = "people")
@AllColumns
public class Person_test extends Entity{

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;

}
