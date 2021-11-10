package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface EmployerService {

	Result add(Employer employer);

	Result delete(int id);

	Result update(Employer employer);

	DataResult<Employer> getById(int id);

	DataResult<List<Employer>> getAll();

}
