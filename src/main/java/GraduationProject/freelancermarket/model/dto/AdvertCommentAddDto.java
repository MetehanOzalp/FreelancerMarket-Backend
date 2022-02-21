package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertCommentAddDto {

	@NotNull(message = "Kullanıcı boş bırakılamaz")
	private int userId;

	@NotNull(message = "İçerik boş bırakılamaz")
	@NotBlank(message = "İçerik boş bırakılamaz")
	private String content;

	@NotNull(message = "İş ilanı boş bırakılamaz")
	private int advertId;

	@NotNull(message = "Puan boş bırakılamaz")
	private Double score;

}
