package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDto {

	@NotNull(message = "İşveren boş bırakılamaz")
	private int employerId;

	@NotNull(message = "İlan boş bırakılamaz")
	private int advertId;

}
