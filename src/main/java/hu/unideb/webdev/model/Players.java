package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Players {

    private int id;
    private Timestamp dob;
    private String firstName;
    private String lastName;
    private int height;
    private int weight;

}
