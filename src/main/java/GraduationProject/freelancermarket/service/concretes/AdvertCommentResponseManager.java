package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.AdvertComment;
import GraduationProject.freelancermarket.entities.AdvertCommentResponse;
import GraduationProject.freelancermarket.model.dto.AdvertCommentResponseAddDto;
import GraduationProject.freelancermarket.repository.AdvertCommentResponseRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentResponseService;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentService;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertCommentResponseManager implements AdvertCommentResponseService {

	private final ModelMapper modelMapper;
	private final AdvertCommentService advertCommentService;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;
	private final AdvertCommentResponseRepository advertCommentResponseRepository;

	@Override
	public Result add(AdvertCommentResponseAddDto advertCommentResponseAddDto) {
		var result = BusinessRules.run(userIdAndTokenUserNameVerification(advertCommentResponseAddDto.getUserId()),
				canCommentResponseCheck(advertCommentResponseAddDto));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AdvertCommentResponse advertCommentResponse = modelMapper.map(advertCommentResponseAddDto,
				AdvertCommentResponse.class);
		advertCommentResponse.setDate(LocalDate.now());
		advertCommentResponseRepository.save(advertCommentResponse);
		return new SuccessResult("Yorum yanıtı eklendi");
	}

	public Result canCommentResponseCheck(AdvertCommentResponseAddDto advertCommentResponseAddDto) {
		var result = advertCommentService.getById(advertCommentResponseAddDto.getAdvertCommentId());
		if (!result.isSuccess()) {
			return new ErrorDataResult<AdvertComment>(result.getMessage());
		}
		if (result.getData().getUserId() == advertCommentResponseAddDto.getUserId()
				|| result.getData().getAdvert().getFreelancerId() == advertCommentResponseAddDto.getUserId()) {
			return new SuccessResult();
		}
		return new ErrorResult("Bu yoruma yanıt veremezsiniz!");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
