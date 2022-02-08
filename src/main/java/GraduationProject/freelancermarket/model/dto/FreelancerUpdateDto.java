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
public class FreelancerUpdateDto {

	@NotNull(message = "id alanı boş bırakılamaz")
	private int id;

	@NotNull
	@NotBlank(message = "İsim alanı boş bırakılamaz")
	private String name;

	@NotNull
	@NotBlank(message = "Soyisim alanı boş bırakılamaz")
	private String surName;

	@NotNull
	@NotBlank(message = "Email alanı boş bırakılamaz")
	private String email;

	@Length(min = 3, max = 500, message = "Hakkımda alanına en fazla 500 karakter girilebilir")
	private String about;

	private String appellation;

}
