package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Players {

    private Date dob;
    private String firstName;
    private String lastName;
    private int height;
    private int weight;

}