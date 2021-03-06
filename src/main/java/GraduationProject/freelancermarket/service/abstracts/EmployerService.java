package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.model.dto.EmployerUpdateDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface EmployerService {

	Result add(Employer employer);

	Result delete(int id);

	Result update(EmployerUpdateDto employerUpdateDto);

	Result imageUpdate(int id, MultipartFile file);

	DataResult<Employer> getById(int id);

	DataResult<List<Employer>> getAll();

}
