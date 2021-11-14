package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.model.dto.AdvertAddDto;
import GraduationProject.freelancermarket.model.dto.AdvertUpdateDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface AdvertService {

	Result add(AdvertAddDto advertAddDto);

	Result delete(int id);

	Result update(AdvertUpdateDto advertUpdateDto);

	DataResult<List<Advert>> getAll();

	DataResult<Advert> getById(int id);

}
