package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.model.dto.AdvertAddDto;
import GraduationProject.freelancermarket.model.dto.AdvertSearchFilter;
import GraduationProject.freelancermarket.model.dto.AdvertFilter;
import GraduationProject.freelancermarket.model.dto.AdvertUpdateDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertService {

	Result add(AdvertAddDto advertAddDto);

	Result delete(int id);

	Result update(AdvertUpdateDto advertUpdateDto);

	DataResult<List<Advert>> getAll();

	DataResult<List<Advert>> getByIdIn(List<Integer> ids);

	DataResult<List<Advert>> getByFreelancerId(int freelancerId);

	DataResult<List<Advert>> getBySubCategoryId(int subCategoryId);

	DataResult<List<Advert>> getMostPopularJobAdverts();

	DataResult<List<Advert>> getByPageNumberAndFilter(int pageNumber, String subCategoryName,
			AdvertFilter advertFilter);

	DataResult<List<Advert>> getByPageNumberAndSearchFilter(int pageNumber, AdvertSearchFilter advertSearchFilter);

	DataResult<Advert> getById(int id);

}
