package com.udacity.jdnd.course3.critter.data.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // We don't expect to perform polymorphic queries for this type
public abstract class User {
    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;

}
