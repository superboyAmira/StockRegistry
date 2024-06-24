package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "issuer_id", nullable = false)
	private Issuer issuer;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@Column(name = "amount", nullable = false)
	private Float amount;
}
