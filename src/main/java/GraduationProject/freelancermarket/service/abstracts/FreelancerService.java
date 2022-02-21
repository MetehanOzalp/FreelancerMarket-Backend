package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.model.dto.FreelancerUpdateDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface FreelancerService {

	Result add(Freelancer freelancer);

	Result update(FreelancerUpdateDto freelancerUpdateDto);

	Result updateAverageScore(Freelancer freelancer);

	Result imageUpdate(int id, MultipartFile file);

	Result delete(int id);

	DataResult<Freelancer> getById(int id);

	DataResult<List<Freelancer>> getAll();

	DataResult<List<Freelancer>> getMostPopularFreelancers();

}
