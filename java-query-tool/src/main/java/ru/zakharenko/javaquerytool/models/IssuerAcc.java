package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "issuer_account")
public class IssuerAcc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "issuer_id", nullable = false)
	private Issuer issuer;

	@Column(name = "currency", nullable = false)
	private String currency;

}
