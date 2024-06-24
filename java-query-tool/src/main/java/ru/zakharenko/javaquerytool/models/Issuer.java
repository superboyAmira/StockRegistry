package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "issuer")
public class Issuer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private UUID id;

	@Column(name = "authorized_capital")
	private Long authorizedCapital;

	@Column(name = "company")
	private String company;

	@Column(name = "citizenship")
	private String citizenship;
}
