package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForLoginDto {

	@NotNull
	@NotBlank(message = "Kullanıcı adı boş bırakılamaz")
	private String userName;

	@NotNull
	@NotBlank(message = "Parola boş bırakılamaz")
	private String password;

}
