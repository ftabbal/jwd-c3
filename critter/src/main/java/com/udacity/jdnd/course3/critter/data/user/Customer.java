package com.udacity.jdnd.course3.critter.data.user;

import com.udacity.jdnd.course3.critter.data.pet.Pet;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends User {
    private String phoneNumber;

    @Type(type = "text") // Same as pet, to be able to store as much info as needed
    private String notes;

    @OneToMany
    private List<Pet> pets;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
