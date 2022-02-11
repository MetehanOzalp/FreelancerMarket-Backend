package GraduationProject.freelancermarket.core.adapters.image;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.SuccessDataResult;

public class CloudinaryManager implements ImageService {

	Map<String, String> environment = System.getenv();
	Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", environment.get("CLOUD_NAME"), "api_key",
			environment.get("API_KEY"), "api_secret", environment.get("API_SECRET")));

	@Override
	public DataResult<?> uploadImage(MultipartFile file) {
		try {
			Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			return new SuccessDataResult<Map>(upload);
		} catch (IOException e) {
			e.printStackTrace();
			return new ErrorDataResult<Map>();
		}
	}

}
