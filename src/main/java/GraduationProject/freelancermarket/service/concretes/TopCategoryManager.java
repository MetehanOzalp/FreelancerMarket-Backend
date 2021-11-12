package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.TopCategory;
import GraduationProject.freelancermarket.model.dto.TopCategoryAddDto;
import GraduationProject.freelancermarket.repository.TopCategoryRepository;
import GraduationProject.freelancermarket.service.abstracts.TopCategoryService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopCategoryManager implements TopCategoryService {

	private final TopCategoryRepository topCategoryRepository;
	private final ModelMapper modelMapper;

	@Override
	public Result add(TopCategoryAddDto topCategoryAddDto) {
		TopCategory topCategory = modelMapper.map(topCategoryAddDto, TopCategory.class);
		topCategoryRepository.save(topCategory);
		return new SuccessResult("Kategori eklendi");
	}

	@Override
	public Result delete(int id) {
		topCategoryRepository.deleteById(id);
		return new SuccessResult("Kategori silindi");
	}

	@Override
	public DataResult<List<TopCategory>> getAll() {
		return new SuccessDataResult<List<TopCategory>>(topCategoryRepository.findAll(), "Kategoriler listelendi");
	}

}
