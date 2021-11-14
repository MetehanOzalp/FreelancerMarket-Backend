package GraduationProject.freelancermarket.service.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.entities.Wallet;
import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.service.abstracts.AuthService;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.service.abstracts.FreelancerService;
import GraduationProject.freelancermarket.service.abstracts.UserService;
import GraduationProject.freelancermarket.service.abstracts.WalletService;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

	private final EmployerService employerService;
	private final FreelancerService freelancerService;
	private final UserService userService;
	private final ModelMapper modelMapper;
	private final WalletService walletService;
	private String imagePath = "https://res.cloudinary.com/metcloud/image/upload/v1636485499/user_el1kyd.png";

	@Override
	public Result registerForEmployer(EmployerForRegisterDto employerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExists(employerForRegisterDto.getEmail()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Employer employer = modelMapper.map(employerForRegisterDto, Employer.class);
		employer.setImagePath(imagePath);
		var added = employerService.add(employer);
		if (!added.isSuccess()) {
			return new ErrorResult(added.getMessage());
		}
		Wallet wallet = Wallet.builder().userId(employer.getId()).balance(0.0).build();
		walletService.add(wallet);
		return new SuccessResult(added.getMessage());
	}

	@Override
	public Result registerForFreelancer(FreelancerForRegisterDto freelancerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExists(freelancerForRegisterDto.getEmail()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		freelancerForRegisterDto.setImagePath(imagePath);
		Freelancer freelancer = modelMapper.map(freelancerForRegisterDto, Freelancer.class);
		var added = freelancerService.add(freelancer);
		if (!added.isSuccess()) {
			return new ErrorResult(added.getMessage());
		}
		Wallet wallet = Wallet.builder().userId(freelancer.getId()).balance(0.0).build();
		walletService.add(wallet);
		return new SuccessResult(added.getMessage());
	}

	public Result checkIfUserExists(String email) {
		var result = userService.getByMail(email);
		if (result.getData() != null) {
			return new ErrorResult("Kullanıcı zaten mevcut");
		}
		return new SuccessResult();
	}

}
