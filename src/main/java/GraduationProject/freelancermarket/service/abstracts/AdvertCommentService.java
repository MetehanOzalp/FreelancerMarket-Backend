package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.AdvertComment;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertCommentService {

	Result add(AdvertComment advertComment);

	Result delete(int id);

	DataResult<List<AdvertComment>> getByAdvertId(int advertId);

}
