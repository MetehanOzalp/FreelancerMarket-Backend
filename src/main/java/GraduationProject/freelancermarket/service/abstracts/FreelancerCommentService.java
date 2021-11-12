package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.FreelancerComment;
import GraduationProject.freelancermarket.model.dto.FreelancerCommentAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface FreelancerCommentService {

	Result add(FreelancerCommentAddDto freelancerCommentAddDto);

	Result delete(int id);

	DataResult<List<FreelancerComment>> getByFreelancerId(int id);

}
