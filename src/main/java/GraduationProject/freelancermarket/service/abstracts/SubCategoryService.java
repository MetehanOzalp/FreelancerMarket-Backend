package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.SubCategory;
import GraduationProject.freelancermarket.model.dto.SubCategoryAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface SubCategoryService {

	Result add(SubCategoryAddDto subCategoryAddDto);

	Result delete(int id);

	DataResult<SubCategory> getByName(String name);

	DataResult<List<SubCategory>> getAll();

	DataResult<List<SubCategory>> getByTopCategoryId(int topCategoryId);

	DataResult<List<SubCategory>> getByTopCategoryName(String topCategoryName);

	DataResult<List<SubCategory>> getMostPopularSubCategories();

}
