package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.FreelancerComment;
import GraduationProject.freelancermarket.repository.FreelancerCommentRepository;
import GraduationProject.freelancermarket.service.abstracts.FreelancerCommentService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreelancerCommentManager implements FreelancerCommentService {

	private final FreelancerCommentRepository freelancerCommentRepository;

	@Override
	public Result add(FreelancerComment freelancerComment) {
		freelancerCommentRepository.save(freelancerComment);
		return new SuccessResult("Yorum eklendi");
	}

	@Override
	public Result delete(int id) {
		freelancerCommentRepository.deleteById(id);
		return new SuccessResult("Yorum silindi");
	}

	@Override
	public DataResult<List<FreelancerComment>> getByFreelancerId(int id) {
		return new SuccessDataResult<List<FreelancerComment>>(freelancerCommentRepository.findByFreelancerId(id),
				"Yorumlar listelendi");
	}

}
