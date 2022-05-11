package GraduationProject.freelancermarket.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "advert_id")
	private int advertId;

	@Column(name = "status")
	private boolean status;

	@Column(name = "createdDate")
	private LocalDate createdDate;

	@Column(name = "price")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "advert_id", insertable = false, updatable = false)
	private Advert advert;

}
