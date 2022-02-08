package GraduationProject.freelancermarket.service.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Freelancer;
import GraduationProject.freelancermarket.model.dto.FreelancerUpdateDto;
import GraduationProject.freelancermarket.repository.FreelancerRepository;
import GraduationProject.freelancermarket.service.abstracts.FreelancerService;
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
public class FreelancerManager implements FreelancerService {

	private final FreelancerRepository freelancerRepository;
	private final UserService userService;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;

	@Override
	public Result add(Freelancer freelancer) {
		freelancerRepository.save(freelancer);
		return new SuccessResult("Freelancer eklendi");
	}

	@Override
	public Result update(FreelancerUpdateDto freelancerUpdateDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(freelancerUpdateDto.getId()),
				checkIfUserExistsByEmail(freelancerUpdateDto.getId(), freelancerUpdateDto.getEmail()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		var freelancer = freelancerRepository.findById(freelancerUpdateDto.getId()).orElse(null);
		if (freelancer == null) {
			return new ErrorResult("Kullanıcı bulunamadı");
		}
		freelancer.setName(freelancerUpdateDto.getName());
		freelancer.setSurName(freelancerUpdateDto.getSurName());
		freelancer.setEmail(freelancerUpdateDto.getEmail());
		freelancer.setAbout(freelancerUpdateDto.getAbout());
		freelancer.setAppellation(freelancerUpdateDto.getAppellation());
		freelancerRepository.save(freelancer);
		return new SuccessResult("Freelancer güncellendi");
	}

	@Override
	public Result delete(int id) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(id));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		freelancerRepository.deleteById(id);
		return new SuccessResult("Freelancer silindi");
	}

	@Override
	public DataResult<Freelancer> getById(int id) {
		return new SuccessDataResult<Freelancer>(freelancerRepository.findById(id).get(), "Freelancer listelendi");
	}

	@Override
	public DataResult<List<Freelancer>> getAll() {
		return new SuccessDataResult<List<Freelancer>>(freelancerRepository.findAll(), "Freelancerlar listelendi");
	}

	@Override
	public DataResult<List<Freelancer>> getMostPopularFreelancers() {
		List<Freelancer> freelancers = freelancerRepository.findAll();
		List<Freelancer> mostPopularFreelancers = new ArrayList<Freelancer>();
		for (int i = 0; i < 20; i++) {
			if (i < freelancers.size()) {
				mostPopularFreelancers.add(freelancers.get(i));
			}
		}
		return new SuccessDataResult<List<Freelancer>>(mostPopularFreelancers);
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
