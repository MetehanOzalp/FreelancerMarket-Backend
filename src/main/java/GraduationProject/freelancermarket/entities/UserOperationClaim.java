package GraduationProject.freelancermarket.entities;

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

import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "user_operation_claims")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user" })
@AllArgsConstructor
@NoArgsConstructor
public class UserOperationClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "claim_name")
	private UserOperationClaimTypeEnum claimName;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

}
