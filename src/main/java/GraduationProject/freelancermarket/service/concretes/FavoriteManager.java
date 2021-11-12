package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Favorite;
import GraduationProject.freelancermarket.model.dto.FavoriteAddDto;
import GraduationProject.freelancermarket.repository.FavoriteRepository;
import GraduationProject.freelancermarket.service.abstracts.FavoriteService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteManager implements FavoriteService {

	private final FavoriteRepository favoriteRepository;
	private final ModelMapper modelMapper;

	@Override
	public Result add(FavoriteAddDto favoriteAddDto) {
		Favorite favorite = modelMapper.map(favoriteAddDto, Favorite.class);
		favoriteRepository.save(favorite);
		return new SuccessResult("Favorilere eklendi");
	}

	@Override
	public Result delete(int id) {
		favoriteRepository.deleteById(id);
		return new SuccessResult("Favorilerden silindi");
	}

	@Override
	public DataResult<List<Favorite>> getByUserId(int id) {
		return new SuccessDataResult<List<Favorite>>(favoriteRepository.findByUserId(id), "Favoriler listelendi");
	}

}
