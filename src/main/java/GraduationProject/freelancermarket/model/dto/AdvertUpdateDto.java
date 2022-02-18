package GraduationProject.freelancermarket.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertUpdateDto {

	@NotNull(message = "id alanı boş bırakılamaz")
	private int id;

	@NotNull(message = "Freelancer boş bırakılamaz")
	private int freelancerId;

	@NotNull(message = "Alt kategori boş bırakılamaz")
	private int subCategoryId;

	@NotNull(message = "İlan başlığı boş bırakılamaz")
	@NotBlank(message = "İlan başlığı boş bırakılamaz")
	private String title;

	@NotNull(message = "İlan fiyatı boş bırakılamaz")
	private Double price;

	@NotNull(message = "İlan açıklaması boş bırakılamaz")
	@NotBlank(message = "İlan açıklaması boş bırakılamaz")
	private String info;

	private MultipartFile imagePath;

}
