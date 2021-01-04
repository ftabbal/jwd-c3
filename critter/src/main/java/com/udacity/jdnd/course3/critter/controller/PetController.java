package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.DTOConverter;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private DTOConverter dtoConverter;

    public PetController(PetService petService) {
        this.petService = petService;
        this.dtoConverter = new DTOConverter();
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = dtoConverter.convert(petDTO, Pet.class);
        petService.savePet(pet);
        return dtoConverter.convert(pet, PetDTO.class);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return dtoConverter.convert(pet, PetDTO.class);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.getAllPets();
        return dtoConverter.convertList(petList, PetDTO.class);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwnerId(ownerId);
        return dtoConverter.convertList(petList, PetDTO.class);
    }
}
