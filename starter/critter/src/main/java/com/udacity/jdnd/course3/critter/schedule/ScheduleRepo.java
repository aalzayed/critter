package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

	List<Schedule> getByPetsId(Long id);

	List<Schedule> getByEmployeesId(Long employeeId);

}
