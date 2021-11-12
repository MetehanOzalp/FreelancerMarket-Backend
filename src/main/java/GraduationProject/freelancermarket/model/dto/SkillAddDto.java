package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillAddDto {

	@NotNull(message = "Freelancer boş bırakılamaz")
	private int freelancerId;

	@NotBlank(message = "Yetenek adı boş bırakılamaz")
	private String name;

}
