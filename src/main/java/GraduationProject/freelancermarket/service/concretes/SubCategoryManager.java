package GraduationProject.freelancermarket.service.concretes;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.SubCategory;
import GraduationProject.freelancermarket.model.dto.SubCategoryAddDto;
import GraduationProject.freelancermarket.repository.SubCategoryRepository;
import GraduationProject.freelancermarket.service.abstracts.SubCategoryService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubCategoryManager implements SubCategoryService {

	private final SubCategoryRepository subCategoryRepository;
	private final ModelMapper modelMapper;

	@Override
	public Result add(SubCategoryAddDto subCategoryAddDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SubCategory subCategory = modelMapper.map(subCategoryAddDto, SubCategory.class);
		subCategoryRepository.save(subCategory);
		return new SuccessResult("Alt kategori eklendi");
	}

	@Override
	public Result delete(int id) {
		subCategoryRepository.deleteById(id);
		return new SuccessResult("Alt kategori silindi");
	}

	@Override
	public DataResult<SubCategory> getByName(String name) {
		var subCategory = subCategoryRepository.findByName(name);
		if (subCategory == null) {
			return new ErrorDataResult<SubCategory>("Kategori bulunamadı");
		}
		return new SuccessDataResult<SubCategory>(subCategory);
	}

	@Override
	public DataResult<List<SubCategory>> getAll() {
		return new SuccessDataResult<List<SubCategory>>(subCategoryRepository.findAll(), "Alt kategoriler listelendi");
	}

	@Override
	public DataResult<List<SubCategory>> getByTopCategoryId(int topCategoryId) {
		return new SuccessDataResult<List<SubCategory>>(subCategoryRepository.findByTopCategoryId(topCategoryId),
				"Alt kategoriler listelendi");
	}

	@Override
	public DataResult<List<SubCategory>> getByTopCategoryName(String topCategoryName) {
		return new SuccessDataResult<List<SubCategory>>(subCategoryRepository.findByTopCategory_Name(topCategoryName));
	}

	@Override
	public DataResult<List<SubCategory>> getMostPopularSubCategories() {
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		List<SubCategory> mostPopularSubCategories = new ArrayList<SubCategory>();
		for (int i = 0; i < 8; i++) {
			if (i < subCategories.size()) {
				mostPopularSubCategories.add(subCategories.get(i));
			}
		}
		return new SuccessDataResult<List<SubCategory>>(mostPopularSubCategories);
	}

}
