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
public class CustomerService {

    private CustomerRepository customerRepository;
    private PetRepository petRepository;

    public CustomerService(CustomerRepository repo, PetRepository petRepository) {
        customerRepository = repo;
        this.petRepository = petRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new ObjectNotFoundException("Cannot find customer with id: " + id);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getByPetId(long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (!optionalPet.isPresent()) {
            throw new ObjectNotFoundException("Cannot find pet with id: " + petId);
        }

        return optionalPet.get().getOwner();
    }

}
