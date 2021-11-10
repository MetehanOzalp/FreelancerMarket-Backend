package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Favorite;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface FavoriteService {

	Result add(Favorite favorite);

	Result delete(int id);

	DataResult<List<Favorite>> getByUserId(int id);

}
