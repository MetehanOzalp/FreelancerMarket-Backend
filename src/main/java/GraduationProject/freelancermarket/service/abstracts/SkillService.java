package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Skill;
import GraduationProject.freelancermarket.model.dto.SkillAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface SkillService {

	DataResult<Skill> add(SkillAddDto skillAddDto);

	Result delete(int id);

	DataResult<List<Skill>> getByFreelancerId(int freelancerId);

}
