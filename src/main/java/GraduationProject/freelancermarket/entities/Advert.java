package GraduationProject.freelancermarket.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "adverts")
@AllArgsConstructor
@NoArgsConstructor
public class Advert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "freelancer_id")
	private int freelanceId;

	@Column(name = "sub_category_id")
	private int subCategoryId;

	@Column(name = "title")
	private String title;

	@Column(name = "price")
	private Double price;

	@Column(name = "info")
	private String info;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "freelancer_id", insertable = false, updatable = false)
	private Freelancer freelancer;

	@ManyToOne
	@JoinColumn(name = "sub_category_id", insertable = false, updatable = false)
	private SubCategory subCategory;

	@OneToMany(mappedBy = "advert")
	private List<Favorite> favorities;

	@OneToMany(mappedBy = "advert")
	private List<Order> orders;

	@OneToMany(mappedBy = "advert")
	private List<AdvertComment> advertComments;

}
