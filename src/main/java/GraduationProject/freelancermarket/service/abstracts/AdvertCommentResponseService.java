package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.model.dto.AdvertCommentResponseAddDto;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertCommentResponseService {

	Result add(AdvertCommentResponseAddDto advertCommentResponseAddDto);

}
