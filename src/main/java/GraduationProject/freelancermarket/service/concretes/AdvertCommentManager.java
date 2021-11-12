package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.AdvertComment;
import GraduationProject.freelancermarket.model.dto.AdvertCommentAddDto;
import GraduationProject.freelancermarket.repository.AdvertCommentRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertCommentManager implements AdvertCommentService {

	private final AdvertCommentRepository advertCommentRepository;
	private final ModelMapper modelMapper;

	@Override
	public Result add(AdvertCommentAddDto advertCommentAddDto) {
		AdvertComment advertComment = modelMapper.map(advertCommentAddDto, AdvertComment.class);
		advertComment.setDate(LocalDate.now());
		advertCommentRepository.save(advertComment);
		return new SuccessResult("Yorum eklendi");
	}

	@Override
	public Result delete(int id) {
		advertCommentRepository.deleteById(id);
		return new SuccessResult("Yorum silindi");
	}

	@Override
	public DataResult<List<AdvertComment>> getByAdvertId(int advertId) {
		return new SuccessDataResult<List<AdvertComment>>(advertCommentRepository.findByAdvertId(advertId),
				"İlanın yorumları listelendi");
	}

}
