package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.UserOperationClaim;
import GraduationProject.freelancermarket.repository.UserOperationClaimRepository;
import GraduationProject.freelancermarket.service.abstracts.UserOperationClaimService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserOperationClaimManager implements UserOperationClaimService {

	private UserOperationClaimRepository userOperationClaimRepository;

	@Override
	public Result add(UserOperationClaim userOperationClaim) {
		userOperationClaimRepository.save(userOperationClaim);
		return new SuccessResult("Yetki eklendi");
	}

	@Override
	public DataResult<List<UserOperationClaim>> getByUserId(int id) {
		return new SuccessDataResult<List<UserOperationClaim>>(userOperationClaimRepository.findByUserId(id),
				"Kullanıcının yetkileri listelendi");
	}

}
