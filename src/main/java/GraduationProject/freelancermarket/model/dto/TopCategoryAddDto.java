package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCategoryAddDto {

	@NotNull(message = "Kategori adı boş bırakılamaz")
	@NotNull(message = "Kategori adı boş bırakılamaz")
	private String name;

}
