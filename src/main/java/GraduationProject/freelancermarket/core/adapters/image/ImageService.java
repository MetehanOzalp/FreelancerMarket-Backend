package GraduationProject.freelancermarket.core.adapters.image;

import org.springframework.web.multipart.MultipartFile;

import GraduationProject.freelancermarket.utils.DataResult;

public interface ImageService {

	DataResult<?> uploadImage(MultipartFile file);

}
