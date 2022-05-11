package GraduationProject.freelancermarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertFilter {

	private Double minPrice;
	private Double maxPrice;
	private int page;

}
