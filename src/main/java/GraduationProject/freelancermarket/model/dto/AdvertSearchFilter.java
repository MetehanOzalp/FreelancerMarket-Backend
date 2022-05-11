package GraduationProject.freelancermarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertSearchFilter {

	private String term;
	private Integer slug;
	private Double minPrice;
	private Double maxPrice;
	private int page;

}
