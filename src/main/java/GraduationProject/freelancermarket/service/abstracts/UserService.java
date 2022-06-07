package GraduationProject.freelancermarket.service.abstracts;

import java.util.HashMap;
import java.util.List;

import GraduationProject.freelancermarket.entities.User;
import GraduationProject.freelancermarket.utils.DataResult;

public interface UserService {

	DataResult<User> getById(int id);

	DataResult<User> getByMail(String mail);

	DataResult<User> getByUserName(String userName);

	DataResult<HashMap<String, String>> getUserImages(List<String> userNames);

}
