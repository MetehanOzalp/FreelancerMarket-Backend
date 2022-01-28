package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Favorite;
import GraduationProject.freelancermarket.model.dto.FavoriteAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface FavoriteService {

	Result add(FavoriteAddDto favoriteAddDto);

	Result delete(int userId, int advertId);

	DataResult<List<Favorite>> getByUserId(int id);

}
