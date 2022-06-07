package GraduationProject.freelancermarket.service.concretes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.User;
import GraduationProject.freelancermarket.repository.UserRepository;
import GraduationProject.freelancermarket.service.abstracts.UserService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

	private final UserRepository userRepository;

	@Override
	public DataResult<User> getById(int id) {
		var user = userRepository.findById(id).orElse(null);
		if (user == null) {
			return new ErrorDataResult<User>("Kullanıcı bulunamadı");
		}
		return new SuccessDataResult<User>(user);
	}

	@Override
	public DataResult<User> getByMail(String mail) {
		var user = userRepository.findByEmail(mail);
		if (user == null) {
			return new ErrorDataResult<User>("Kullanıcı bulunamadı");
		}
		return new SuccessDataResult<User>(user);
	}

	@Override
	public DataResult<User> getByUserName(String userName) {
		var user = userRepository.findByUserName(userName);
		if (user == null) {
			return new ErrorDataResult<User>("Kullanıcı bulunamadı");
		}
		return new SuccessDataResult<User>(user);
	}

	@Override
	public DataResult<HashMap<String, String>> getUserImages(List<String> userNames) {
		var users = userRepository.findByUserNameIn(userNames);
		if (users.size() == 0) {
			return new ErrorDataResult<HashMap<String, String>>("Kullanıcılar bulunamadı");
		}
		HashMap<String, String> images = new HashMap<String, String>();
		for (User user : users) {
			try {
				Field field = user.getClass().getDeclaredField("imagePath");
				field.setAccessible(true);
				images.put(user.getUserName(), field.get(user).toString());
			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			} catch (NoSuchFieldException ex) {
				throw new RuntimeException(ex);
			}
		}
		return new SuccessDataResult<HashMap<String, String>>(images);
	}

}
