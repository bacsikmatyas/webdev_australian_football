package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="teams", schema = "AustralianFootball")
public class TeamsEntity {

    //model > repo > dao > dao impl > service > service impl
    @Id
    @Column(name = "tid")
    private int id;

    @Column(name = "tname")
    private String teamName;

}
