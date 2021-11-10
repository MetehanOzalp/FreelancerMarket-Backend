package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.repository.AdvertRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertManager implements AdvertService {

	private final AdvertRepository advertRepository;

	@Override
	public Result add(Advert advert) {
		advertRepository.save(advert);
		return new SuccessResult("İş ilanı eklendi");
	}

	@Override
	public Result delete(int id) {
		advertRepository.deleteById(id);
		return new SuccessResult("İş ilanı silindi");
	}

	@Override
	public Result update(Advert advert) {
		advertRepository.save(advert);
		return new SuccessResult("İş ilanı güncellendi");
	}

	@Override
	public DataResult<List<Advert>> getAll() {
		return new SuccessDataResult<List<Advert>>(advertRepository.findAll(), "İş ilanları listelendi");
	}

	@Override
	public DataResult<Advert> getById(int id) {
		return new SuccessDataResult<Advert>(advertRepository.findById(id).get(), "İş ilanı listelendi");
	}

}
