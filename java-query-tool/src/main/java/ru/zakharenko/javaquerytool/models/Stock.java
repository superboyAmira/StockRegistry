package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "registry_id", nullable = false)
	private Registry registry;

	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "issuer_account_id", nullable = false)
	private IssuerAcc issuerAccount;

	@Column(name = "quote", nullable = false)
	private Long quote;

	@Column(name = "form")
	private String form;

	@Column(name = "nominal_value")
	private Long nominalValue;

	@Column(name = "stock_quote_change", nullable = false)
	private Long stockQuoteChange = 0L;
}
