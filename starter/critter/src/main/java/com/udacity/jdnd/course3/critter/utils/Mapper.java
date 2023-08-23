package com.udacity.jdnd.course3.critter.utils;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

public class Mapper {

	// ** Customer ** //
	public static Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);

		return customer;
	}

	public static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		List<Pet> pets = customer.getPets();
		List<Long> petIds = new LinkedList<>();

		if (pets != null) {
			petIds = pets.stream().map(Pet::getId).toList();
		}

		customerDTO.setPetIds(petIds);
		return customerDTO;
	}

	public static List<CustomerDTO> convertCustomerToCustomerDTO(List<Customer> customers) {
		return customers.stream().map(Mapper::convertCustomerToCustomerDTO).toList();
	}

	// ** Employee ** //
	public static Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);

		return employee;
	}

	public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employee, employeeDTO);
		return employeeDTO;
	}

	public static List<EmployeeDTO> convertEmployeeToEmployeeDTO(List<Employee> employees) {
		return employees.stream().map(Mapper::convertEmployeeToEmployeeDTO).toList();
	}

	// ** Pet ** //
	public static Pet convertPetDTOToPet(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet);

		return pet;
	}

	public static PetDTO convertPetToPetDTO(Pet pet) {
		PetDTO petDTO = new PetDTO();
		BeanUtils.copyProperties(pet, petDTO);

		Customer customer = pet.getCustomer();
		petDTO.setOwnerId(customer.getId());

		return petDTO;
	}

	public static List<PetDTO> convertPetToPetDTO(List<Pet> pet) {
		return pet.stream().map(Mapper::convertPetToPetDTO).toList();
	}

	// ** Schedule ** //
	public static Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO, List<Employee> employees, List<Pet> pets) {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);

		schedule.setActivities(scheduleDTO.getActivities());
		schedule.setEmployees(employees);
		schedule.setPets(pets);

		return schedule;
	}

	public static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(schedule, scheduleDTO);

		// Map employees
		List<Employee> employees = schedule.getEmployees();
		List<Long> employeesIds = new LinkedList<>();
		if (employees != null) {
			employeesIds = employees.stream().map(Employee::getId).toList();
		}

		scheduleDTO.setEmployeeIds(employeesIds);

		// Map Pets
		List<Pet> pets = schedule.getPets();
		List<Long> petsIds = new LinkedList<>();
		if (pets != null) {
			petsIds = pets.stream().map(Pet::getId).toList();
		}

		scheduleDTO.setPetIds(petsIds);

		return scheduleDTO;
	}

	public static List<ScheduleDTO> convertScheduleToScheduleDTO(List<Schedule> schedules) {
		return schedules.stream().map(Mapper::convertScheduleToScheduleDTO).toList();
	}
}
