package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryAddDto {

	@NotNull(message = "Üst kategori boş bırakılamaz")
	private int topCategoryId;

	@NotNull(message = "Alt kategori adı boş bırakılamaz")
	@NotBlank(message = "Alt kategori adı boş bırakılamaz")
	private String name;

}
