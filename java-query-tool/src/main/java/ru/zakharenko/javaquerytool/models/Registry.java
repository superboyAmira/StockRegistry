package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "registry")
public class Registry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private UUID id;


	@Column(name = "name")
	private String name;

	@Column(name = "owner")
	private String owner;
}
