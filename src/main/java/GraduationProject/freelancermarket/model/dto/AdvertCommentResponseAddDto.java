package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertCommentResponseAddDto {

	@NotNull(message = "Kullanıcı boş bırakılamaz")
	private int userId;

	@NotNull(message = "İçerik boş bırakılamaz")
	@NotBlank(message = "İçerik boş bırakılamaz")
	private String content;

	@NotNull(message = "Yorum boş bırakılamaz")
	private int advertCommentId;

}
