package GraduationProject.freelancermarket.service.concretes;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.entities.UserOperationClaim;
import GraduationProject.freelancermarket.entities.Wallet;
import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.UserForLoginDto;
import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;
import GraduationProject.freelancermarket.service.abstracts.AuthService;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.service.abstracts.FreelancerService;
import GraduationProject.freelancermarket.service.abstracts.OperationClaimService;
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
	private final OperationClaimService operationClaimService;
	private final PasswordEncoder passwordEncoder;
	private String imagePath = "https://res.cloudinary.com/metcloud/image/upload/v1636485499/user_el1kyd.png";

	@Override
	public Result registerForEmployer(EmployerForRegisterDto employerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExistsByEmail(employerForRegisterDto.getEmail()),
				checkIfUserExistsByUserName(employerForRegisterDto.getUserName()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Employer employer = modelMapper.map(employerForRegisterDto, Employer.class);
		employer.setImagePath(imagePath);
		employer.setPassword(getEncodedPassword(employer.getPassword()));
		var businessRules = BusinessRules.run(employerService.add(employer), walletAdd(employer.getId()),
				roleAdd(employer.getId(), UserOperationClaimTypeEnum.ROLE_EMPLOYER));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		return new SuccessResult("İşveren eklendi");
	}

	@Override
	public Result registerForFreelancer(FreelancerForRegisterDto freelancerForRegisterDto) {
		var result = BusinessRules.run(checkIfUserExistsByEmail(freelancerForRegisterDto.getEmail()),
				checkIfUserExistsByUserName(freelancerForRegisterDto.getUserName()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Freelancer freelancer = modelMapper.map(freelancerForRegisterDto, Freelancer.class);
		freelancer.setPassword(getEncodedPassword(freelancer.getPassword()));
		freelancer.setImagePath(imagePath);
		var businessRules = BusinessRules.run(freelancerService.add(freelancer), walletAdd(freelancer.getId()),
				roleAdd(freelancer.getId(), UserOperationClaimTypeEnum.ROLE_FREELANCER));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		return new SuccessResult("Freelancer eklendi");
	}

	@Override
	public Result login(UserForLoginDto userForLoginDto) {
		var userToCheck = userService.getByUserName(userForLoginDto.getUserName());
		if (!userToCheck.isSuccess()) {
			return new ErrorResult(userToCheck.getMessage());
		}
		if (!passwordEncoder.matches(userForLoginDto.getPassword(), userToCheck.getData().getPassword())) {
			return new ErrorResult("Parola yanlış");
		}
		return new SuccessResult();
	}

	public Result roleAdd(int userId, UserOperationClaimTypeEnum userOperationClaimTypeEnum) {
		var operationClaim = operationClaimService.getByOperationClaimType(userOperationClaimTypeEnum);
		if (!operationClaim.isSuccess()) {
			return new ErrorResult(operationClaim.getMessage());
		}
		UserOperationClaim userOperationClaim = UserOperationClaim.builder().userId(userId)
				.claimId(operationClaim.getData().getId()).build();
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

	public Result checkIfUserExistsByEmail(String email) {
		var result = userService.getByMail(email);
		if (result.getData() != null) {
			return new ErrorResult("Kullanıcı zaten mevcut");
		}
		return new SuccessResult();
	}

	public Result checkIfUserExistsByUserName(String userName) {
		var result = userService.getByMail(userName);
		if (result.getData() != null) {
			return new ErrorResult("Kullanıcı zaten mevcut");
		}
		return new SuccessResult();
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
