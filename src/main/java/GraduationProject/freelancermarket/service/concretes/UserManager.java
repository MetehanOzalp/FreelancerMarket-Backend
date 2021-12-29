package GraduationProject.freelancermarket.service.concretes;

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

}
