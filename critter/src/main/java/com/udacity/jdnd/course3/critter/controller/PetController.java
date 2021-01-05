package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.DTOConverter;
import com.udacity.jdnd.course3.critter.data.entity.Customer;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private CustomerService customerService;

    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petDTO.toEntity();
        Customer owner = customerService.getCustomer(petDTO.getOwnerId());
        pet.setOwner(owner);
        pet = petService.savePet(pet);
        return PetDTO.fromEntity(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return PetDTO.fromEntity(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.getAllPets();
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet p : petList) {
            petDTOList.add(PetDTO.fromEntity(p));
        }
        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwnerId(ownerId);

        List<PetDTO> petDTOList = new ArrayList<>();

        for (Pet p : petList)
            petDTOList.add(PetDTO.fromEntity(p));

        return petDTOList;
    }
}
