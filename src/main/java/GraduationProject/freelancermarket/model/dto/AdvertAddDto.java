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
public class AdvertAddDto {

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

	@NotNull(message = "İlan resmi boş bırakılamaz")
	@NotBlank(message = "İlan resmi boş bırakılamaz")
	private MultipartFile imagePath;

}
