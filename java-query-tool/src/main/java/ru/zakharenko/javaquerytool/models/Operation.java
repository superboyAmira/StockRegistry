package ru.zakharenko.javaquerytool.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "operation")
public class Operation {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Long id;

		@ManyToOne
		@JoinColumn(name = "seller_subject_id", nullable = false)
		private Subject sellerSubject;

		@ManyToOne
		@JoinColumn(name = "buyer_subject_id", nullable = false)
		private Subject buyerSubject;

		@ManyToOne
		@JoinColumn(name = "registry_id", nullable = false)
		private Registry registry;

		@ManyToOne
		@JoinColumn(name = "stock_id", nullable = false)
		private Stock stock;

		@Column(name = "operation_date", nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		private Date operationDate;

		public Operation() {
			operationDate = new Date();
		}
}
