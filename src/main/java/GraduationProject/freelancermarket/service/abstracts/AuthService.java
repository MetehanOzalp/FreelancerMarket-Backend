package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.utils.Result;

public interface AuthService {

	Result registerForEmployer(EmployerForRegisterDto employerForRegisterDto);

	Result registerForFreelancer(FreelancerForRegisterDto freelancerForRegisterDto);

}
