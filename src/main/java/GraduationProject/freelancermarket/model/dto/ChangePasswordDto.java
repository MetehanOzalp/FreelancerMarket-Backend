package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

	@NotNull(message = "id alanı boş bırakılamaz")
	private int userId;

	@NotNull
	@NotBlank(message = "Eski parola boş bırakılamaz")
	private String oldPassword;

	@NotNull
	@NotBlank(message = "Yeni parola boş bırakılamaz")
	@Length(min = 8, max = 20, message = "Yeni parola alanı en az 8 en fazla 20 karakter olabilir")
	private String newPassword;

	@NotNull
	@NotBlank(message = "Parola tekrarı boş bırakılamaz")
	@Length(min = 8, max = 20, message = "Yeni parola alanı en az 8 en fazla 20 karakter olabilir")
	private String newPasswordRepeat;

}
