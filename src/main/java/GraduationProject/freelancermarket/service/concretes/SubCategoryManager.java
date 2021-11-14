package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.SubCategory;
import GraduationProject.freelancermarket.model.dto.SubCategoryAddDto;
import GraduationProject.freelancermarket.repository.SubCategoryRepository;
import GraduationProject.freelancermarket.service.abstracts.SubCategoryService;
import GraduationProject.freelancermarket.utils.DataResult;
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
	public DataResult<List<SubCategory>> getAll() {
		return new SuccessDataResult<List<SubCategory>>(subCategoryRepository.findAll(), "Alt kategoriler listelendi");
	}

	@Override
	public DataResult<List<SubCategory>> getByTopCategoryId(int topCategoryId) {
		return new SuccessDataResult<List<SubCategory>>(subCategoryRepository.findByTopCategoryId(topCategoryId),
				"Alt kategoriler listelendi");
	}

}
