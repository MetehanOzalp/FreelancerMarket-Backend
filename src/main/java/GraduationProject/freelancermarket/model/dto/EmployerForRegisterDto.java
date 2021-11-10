package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForRegisterDto {

	@NotNull
	@NotBlank(message = "İsim alanı boş bırakılamaz")
	private String name;

	@NotNull
	@NotBlank(message = "Soyisim alanı boş bırakılamaz")
	private String surname;

	@NotNull
	@NotBlank(message = "Kullanıcı adı alanı boş bırakılamaz")
	private String userName;

	@NotNull
	@NotBlank(message = "Email alanı boş bırakılamaz")
	private String email;

	@NotNull
	@NotBlank(message = "Parola alanı boş bırakılamaz")
	private String password;

	private String imagePath;

}
