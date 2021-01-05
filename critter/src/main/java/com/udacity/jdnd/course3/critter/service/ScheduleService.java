package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Employee;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.data.entity.Schedule;
import com.udacity.jdnd.course3.critter.data.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.data.repository.PetRepository;
import com.udacity.jdnd.course3.critter.data.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository,
                           PetRepository petRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    public Schedule save(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return savedSchedule;
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findAllByEmployeesContaining(employee);
    }

    public List<Schedule> getScheduleForPet(long petId) {
        Pet pet = petRepository.getOne(petId);
        return getScheduleForPet(pet);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Pet> petList = petRepository.findAllByOwnerId(customerId);
        List<Schedule> scheduleList = new ArrayList<>();
        for (Pet pet : petList) {
            scheduleList.addAll(getScheduleForPet(pet));
        }

        return scheduleList;
    }

    private List<Schedule> getScheduleForPet(Pet pet) {
        return scheduleRepository.findAllByPetsContaining(pet);
    }

}
