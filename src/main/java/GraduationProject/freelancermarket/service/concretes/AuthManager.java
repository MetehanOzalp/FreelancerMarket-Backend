package GraduationProject.freelancermarket.service.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.entities.UserOperationClaim;
import GraduationProject.freelancermarket.entities.Wallet;
import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;
import GraduationProject.freelancermarket.service.abstracts.AuthService;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.service.abstracts.FreelancerService;
import GraduationProject.freelancermarket.service.abstracts.UserOperationClaimService;
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
	private final UserOperationClaimService userOperationClaimService;
	private String imagePath = "https://res.cloudinary.com/metcloud/image/upload/v1636485499/user_el1kyd.png";

	@Override
	public Result registerForEmployer(EmployerForRegisterDto employerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExists(employerForRegisterDto.getEmail()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Employer employer = modelMapper.map(employerForRegisterDto, Employer.class);
		employer.setImagePath(imagePath);
		var businessRules = BusinessRules.run(employerService.add(employer), walletAdd(employer.getId()),
				roleAdd(employer.getId(), UserOperationClaimTypeEnum.ROLE_EMPLOYER));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		return new SuccessResult("İşveren eklendi");
	}

	@Override
	public Result registerForFreelancer(FreelancerForRegisterDto freelancerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExists(freelancerForRegisterDto.getEmail()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		freelancerForRegisterDto.setImagePath(imagePath);
		Freelancer freelancer = modelMapper.map(freelancerForRegisterDto, Freelancer.class);
		var businessRules = BusinessRules.run(freelancerService.add(freelancer), walletAdd(freelancer.getId()),
				roleAdd(freelancer.getId(), UserOperationClaimTypeEnum.ROLE_FREELANCER));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		return new SuccessResult("Freelancer eklendi");
	}

	public Result roleAdd(int userId, UserOperationClaimTypeEnum userOperationClaimTypeEnum) {
		UserOperationClaim userOperationClaim = UserOperationClaim.builder().userId(userId)
				.claimName(userOperationClaimTypeEnum).build();
		var result = userOperationClaimService.add(userOperationClaim);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

	public Result walletAdd(int userId) {
		Wallet wallet = Wallet.builder().userId(userId).balance(0.0).build();
		var result = walletService.add(wallet);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

	public Result checkIfUserExists(String email) {
		var result = userService.getByMail(email);
		if (result.getData() != null) {
			return new ErrorResult("Kullanıcı zaten mevcut");
		}
		return new SuccessResult();
	}

}
