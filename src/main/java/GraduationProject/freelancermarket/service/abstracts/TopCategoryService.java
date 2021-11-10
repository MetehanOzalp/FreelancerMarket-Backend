package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.TopCategory;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface TopCategoryService {

	Result add(TopCategory topCategory);

	Result delete(int id);

	DataResult<List<TopCategory>> getAll();

}
