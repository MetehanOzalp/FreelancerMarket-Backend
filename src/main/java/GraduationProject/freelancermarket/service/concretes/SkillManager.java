package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Skill;
import GraduationProject.freelancermarket.model.dto.SkillAddDto;
import GraduationProject.freelancermarket.repository.SkillRepository;
import GraduationProject.freelancermarket.service.abstracts.SkillService;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillManager implements SkillService {

	private final SkillRepository skillRepository;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;
	private final ModelMapper modelMapper;

	@Override
	public DataResult<Skill> add(SkillAddDto skillAddDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(skillAddDto.getFreelancerId()));
		if (businessRules != null) {
			return new ErrorDataResult<Skill>(businessRules.getMessage());
		}
		Skill skill = modelMapper.map(skillAddDto, Skill.class);
		var result = skillRepository.save(skill);
		return new SuccessDataResult<Skill>(result, "Yetenek eklendi");
	}

	@Override
	public Result delete(int id) {
		var skill = skillRepository.findById(id).orElse(null);
		if (skill == null) {
			return new ErrorResult("Yetenek bulunamadı");
		}
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(skill.getFreelancerId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		skillRepository.deleteById(id);
		return new SuccessResult("Yetenek silindi");
	}

	@Override
	public DataResult<List<Skill>> getByFreelancerId(int freelancerId) {
		return new SuccessDataResult<List<Skill>>(skillRepository.findByFreelancerId(freelancerId),
				"Freelancerın yetenekleri listelendi");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
