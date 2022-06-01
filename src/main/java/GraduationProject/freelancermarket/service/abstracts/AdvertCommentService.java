package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.AdvertComment;
import GraduationProject.freelancermarket.model.dto.AdvertCommentAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertCommentService {

	Result add(AdvertCommentAddDto advertCommentAddDto);

	Result delete(int id);

	DataResult<AdvertComment> getById(int id);

	DataResult<List<AdvertComment>> getByAdvertId(int advertId);

	DataResult<List<AdvertComment>> getByFreelancerId(int freelancerId);

}
