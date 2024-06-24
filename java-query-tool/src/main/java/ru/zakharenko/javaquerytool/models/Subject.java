package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private UUID id;

	@Column(name = "contact_info")
	private String contact_info;

	@Column(name = "subject_type")
	private String subject_type;
}
