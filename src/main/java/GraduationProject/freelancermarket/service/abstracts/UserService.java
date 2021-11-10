package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.entities.User;
import GraduationProject.freelancermarket.utils.DataResult;

public interface UserService {

	DataResult<User> getByMail(String mail);

}
