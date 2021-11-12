package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteAddDto {

	@NotNull(message = "Kullanıcı boş bırakılamaz")
	private int userId;

	@NotNull(message = "İlan boş bırakılamaz")
	private int advertId;

}
