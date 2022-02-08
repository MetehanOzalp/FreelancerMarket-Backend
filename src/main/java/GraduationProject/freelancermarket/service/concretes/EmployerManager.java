package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Employer;
import GraduationProject.freelancermarket.model.dto.EmployerUpdateDto;
import GraduationProject.freelancermarket.repository.EmployerRepository;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.service.abstracts.UserService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployerManager implements EmployerService {

	private final EmployerRepository employerRepository;
	private final UserService userService;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;

	@Override
	public Result add(Employer employer) {
		employerRepository.save(employer);
		return new SuccessResult("İşveren eklendi");
	}

	@Override
	public Result delete(int id) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(id));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		employerRepository.deleteById(id);
		return new SuccessResult("İşveren silindi");
	}

	@Override
	public Result update(EmployerUpdateDto employerUpdateDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(employerUpdateDto.getId()),
				checkIfUserExistsByEmail(employerUpdateDto.getId(), employerUpdateDto.getEmail()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		var employer = employerRepository.findById(employerUpdateDto.getId()).orElse(null);
		if (employer == null) {
			return new ErrorResult("Kullanıcı bulunamadı");
		}
		employer.setName(employerUpdateDto.getName());
		employer.setSurName(employerUpdateDto.getSurName());
		employer.setEmail(employerUpdateDto.getEmail());
		employer.setAbout(employerUpdateDto.getAbout());
		employerRepository.save(employer);
		return new SuccessResult("İşveren güncellendi");
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(employerRepository.findById(id).get(), "İşveren listelendi");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(employerRepository.findAll(), "İşverenler listelendi");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

	public Result checkIfUserExistsByEmail(int id, String email) {
		var result = userService.getByMail(email);
		if ((result.getData() != null) && (id != result.getData().getId())) {
			return new ErrorResult("Bu email daha önceden alınmış.");
		}
		return new SuccessResult();
	}

}
