package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.SubCategory;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface SubCategoryService {

	Result add(SubCategory subCategory);

	Result delete(int id);

	DataResult<List<SubCategory>> getAll();

	DataResult<List<SubCategory>> getByTopCategoryId(int topCategoryId);

}