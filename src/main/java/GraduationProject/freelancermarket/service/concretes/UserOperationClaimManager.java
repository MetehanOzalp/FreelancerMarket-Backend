package GraduationProject.freelancermarket.service.concretes;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.UserOperationClaim;
import GraduationProject.freelancermarket.repository.UserOperationClaimRepository;
import GraduationProject.freelancermarket.service.abstracts.UserOperationClaimService;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserOperationClaimManager implements UserOperationClaimService {

	private final UserOperationClaimRepository userOperationClaimRepository;

	@Override
	public Result add(UserOperationClaim userOperationClaim) {
		userOperationClaimRepository.save(userOperationClaim);
		return new SuccessResult("Kullanıcı rolü eklendi");
	}

}
