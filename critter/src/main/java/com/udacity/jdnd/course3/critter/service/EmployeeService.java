package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Employee;
import com.udacity.jdnd.course3.critter.data.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.data.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repo) {
        employeeRepository = repo;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new ObjectNotFoundException("Cannot find employee with id: " + id);
        }
    }

    public List<Employee> getEmployeesByDateAndSkill(DayOfWeek day, Set<EmployeeSkill> skillSet) {
        List<Employee> employeesAvailableForDay = employeeRepository.findAllByDaysAvailableContains(day);
        List<Employee> matchingEmployees = new ArrayList<>();

        for (Employee e : employeesAvailableForDay) {
            if (e.getSkills().containsAll(skillSet))
                matchingEmployees.add(e);
        }
        return matchingEmployees;
    }
}
