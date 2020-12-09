package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="players", schema = "AustralianFootball")
public class PlayersEntity {
    @Id
    @Column(name = "pid")
    private int id;

    @Column(name = "dob")
    private Timestamp dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

}
