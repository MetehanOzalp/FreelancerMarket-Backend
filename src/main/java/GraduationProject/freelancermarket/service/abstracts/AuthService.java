package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.model.dto.ChangePasswordDto;
import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.UserForLoginDto;
import GraduationProject.freelancermarket.utils.Result;

public interface AuthService {

	Result registerForEmployer(EmployerForRegisterDto employerForRegisterDto);

	Result registerForFreelancer(FreelancerForRegisterDto freelancerForRegisterDto);

	Result login(UserForLoginDto userForLoginDto);

	Result changePassword(ChangePasswordDto changePasswordDto);

}
