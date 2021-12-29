package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.model.dto.AdvertAddDto;
import GraduationProject.freelancermarket.model.dto.AdvertUpdateDto;
import GraduationProject.freelancermarket.repository.AdvertRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertManager implements AdvertService {

	private final AdvertRepository advertRepository;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;
	private final ModelMapper modelMapper;

	@Override
	public Result add(AdvertAddDto advertAddDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(advertAddDto.getFreelancerId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		Advert advert = modelMapper.map(advertAddDto, Advert.class);
		advert.setDate(LocalDate.now());
		advertRepository.save(advert);
		return new SuccessResult("İş ilanı eklendi");
	}

	@Override
	public Result delete(int id) {
		var advert = advertRepository.findById(id).orElse(null);
		if (advert == null) {
			return new ErrorResult("İş ilanı bulunamadı");
		}
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(advert.getFreelancerId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		advertRepository.deleteById(id);
		return new SuccessResult("İş ilanı silindi");
	}

	@Override
	public Result update(AdvertUpdateDto advertUpdateDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(advertUpdateDto.getFreelancerId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		Advert advert = modelMapper.map(advertUpdateDto, Advert.class);
		advertRepository.save(advert);
		return new SuccessResult("İş ilanı güncellendi");
	}

	@Override
	public DataResult<List<Advert>> getAll() {
		return new SuccessDataResult<List<Advert>>(advertRepository.findAll(), "İş ilanları listelendi");
	}

	@Override
	public DataResult<Advert> getById(int id) {
		var result = advertRepository.findById(id).orElse(null);
		if (result == null) {
			return new ErrorDataResult<Advert>(result, "İş ilanı bulunamadı");
		}
		return new SuccessDataResult<Advert>(result, "İş ilanı listelendi");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
