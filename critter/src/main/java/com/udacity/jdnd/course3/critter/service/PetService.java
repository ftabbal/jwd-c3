package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Customer;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.data.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.data.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        Customer owner = pet.getOwner();
        owner.addPet(savedPet);
        customerRepository.save(owner);
        return pet;
    }

    public Pet getPet(long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        } else {
            throw new ObjectNotFoundException("Cannot find pet with id: " + id);
        }
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
