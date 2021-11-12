package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.TopCategory;
import GraduationProject.freelancermarket.model.dto.TopCategoryAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface TopCategoryService {

	Result add(TopCategoryAddDto topCategoryAddDto);

	Result delete(int id);

	DataResult<List<TopCategory>> getAll();

}
