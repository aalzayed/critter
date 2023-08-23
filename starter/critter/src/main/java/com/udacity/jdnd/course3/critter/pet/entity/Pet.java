package com.udacity.jdnd.course3.critter.pet.entity;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.entity.Customer;

import lombok.*;

@Entity
@Setter
@Getter
@ToString
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "NVARCHAR(50)")
	@Nationalized
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PetType type;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "schedule_id", nullable = true)
//	private Schedule schedule;

	@Column(nullable = true)
	private LocalDate birthDate;

	@Column(nullable = true)
	private String notes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

}
