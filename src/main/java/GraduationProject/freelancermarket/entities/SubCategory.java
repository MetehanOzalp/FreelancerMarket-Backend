package GraduationProject.freelancermarket.entities;

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
@Table(name = "sub_categories")
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "top_category_id")
	private int topCategoryId;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "top_category_id", insertable = false, updatable = false)
	private TopCategory topCategory;

	@OneToMany(mappedBy = "subCategory")
	private List<Advert> adverts;

}
