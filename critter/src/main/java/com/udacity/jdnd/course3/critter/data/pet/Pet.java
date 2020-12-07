package com.udacity.jdnd.course3.critter.data.pet;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private long id;

    private PetType type;

    @Nationalized
    private String name;
    private long ownerId;
    private LocalDate birthDate;

    @Type(type = "text") // Using a text type to be able to store as much info as needed
    private String notes;
}
