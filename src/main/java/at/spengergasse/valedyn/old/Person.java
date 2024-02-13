package at.spengergasse.valedyn.old;

import java.time.LocalDate;

public record Person(
    String firstName,
    String lastName,
    LocalDate birthdate
) {

}
