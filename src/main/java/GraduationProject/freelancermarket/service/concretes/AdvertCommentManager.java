package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.AdvertComment;
import GraduationProject.freelancermarket.model.dto.AdvertCommentAddDto;
import GraduationProject.freelancermarket.repository.AdvertCommentRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentService;
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
public class AdvertCommentManager implements AdvertCommentService {

	private final AdvertCommentRepository advertCommentRepository;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;
	private final ModelMapper modelMapper;

	@Autowired
	private AdvertService advertService;

	@Override
	public Result add(AdvertCommentAddDto advertCommentAddDto) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(advertCommentAddDto.getUserId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AdvertComment advertComment = modelMapper.map(advertCommentAddDto, AdvertComment.class);
		advertComment.setDate(LocalDate.now());
		advertCommentRepository.save(advertComment);
		advertService.updateScore(advertCommentAddDto.getAdvertId());
		return new SuccessResult("Yorum eklendi");
	}

	@Override
	public Result delete(int id) {
		var advertComment = advertCommentRepository.findById(id).orElse(null);
		if (advertComment == null) {
			return new ErrorResult("Yorum bulunamadı");
		}
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(advertComment.getUserId()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		advertCommentRepository.deleteById(id);
		advertService.updateScore(advertComment.getAdvertId());
		return new SuccessResult("Yorum silindi");
	}

	@Override
	public DataResult<AdvertComment> getById(int id) {
		var result = advertCommentRepository.findById(id).orElse(null);
		if (result == null) {
			return new ErrorDataResult<AdvertComment>("Yorum bulunamadı");
		}
		return new SuccessDataResult<AdvertComment>(result);
	}

	@Override
	public DataResult<List<AdvertComment>> getByAdvertId(int advertId) {
		return new SuccessDataResult<List<AdvertComment>>(advertCommentRepository.findByAdvertId(advertId),
				"İlanın yorumları listelendi");
	}

	@Override
	public DataResult<List<AdvertComment>> getByFreelancerId(int freelancerId) {
		return new SuccessDataResult<List<AdvertComment>>(
				advertCommentRepository.findByAdvert_FreelancerId(freelancerId), "Freelancerın yorumları listelendi");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
