package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Skill;
import GraduationProject.freelancermarket.repository.SkillRepository;
import GraduationProject.freelancermarket.service.abstracts.SkillService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillManager implements SkillService {

	private final SkillRepository skillRepository;

	@Override
	public Result add(Skill skill) {
		skillRepository.save(skill);
		return new SuccessResult("Yetenek eklendi");
	}

	@Override
	public Result delete(int id) {
		skillRepository.deleteById(id);
		return new SuccessResult("Yetenek silindi");
	}

	@Override
	public DataResult<List<Skill>> getByFreelancerId(int freelancerId) {
		return new SuccessDataResult<List<Skill>>(skillRepository.findByFreelancerId(freelancerId),
				"Freelancerın yetenekleri listelendi");
	}

}
