package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface FreelancerService {

	Result add(Freelancer freelancer);

	Result update(Freelancer freelancer);

	Result delete(int id);

	DataResult<Freelancer> getById(int id);

	DataResult<List<Freelancer>> getAll();

}
