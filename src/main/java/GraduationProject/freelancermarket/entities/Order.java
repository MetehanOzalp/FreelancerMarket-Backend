package GraduationProject.freelancermarket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "employer_id")
	private int employerId;

	@Column(name = "advert_id")
	private int advertId;

	@Column(name = "status")
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "employer_id", insertable = false, updatable = false)
	private Employer employer;

	@ManyToOne
	@JoinColumn(name = "advert_id", insertable = false, updatable = false)
	private Advert advert;

}