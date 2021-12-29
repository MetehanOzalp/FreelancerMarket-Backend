package GraduationProject.freelancermarket.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import GraduationProject.freelancermarket.model.enums.WalletTransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "wallet_transactions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user" })
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_name")
	private WalletTransactionTypeEnum transactionName;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

}
