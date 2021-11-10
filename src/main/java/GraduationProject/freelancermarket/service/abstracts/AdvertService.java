package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertService {

	Result add(Advert advert);

	Result delete(int id);

	Result update(Advert advert);

	DataResult<List<Advert>> getAll();

	DataResult<Advert> getById(int id);

}
