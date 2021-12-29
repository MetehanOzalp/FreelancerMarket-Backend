package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.utils.Result;

public interface TokenUserNameAndIdValidationService {

	Result userIdAndTokenUserNameVerification(int userId);

}
