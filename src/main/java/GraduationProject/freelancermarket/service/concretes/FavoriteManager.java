package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Favorite;
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

	@Override
	public Result add(Favorite favorite) {
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
