package GraduationProject.freelancermarket.service.concretes;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.OperationClaim;
import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;
import GraduationProject.freelancermarket.repository.OperationClaimRepository;
import GraduationProject.freelancermarket.service.abstracts.OperationClaimService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationClaimManager implements OperationClaimService {

	private final OperationClaimRepository operationClaimRepository;

	@Override
	public DataResult<OperationClaim> getByOperationClaimType(UserOperationClaimTypeEnum userOperationClaimTypeEnum) {
		var operationClaim = operationClaimRepository.findByClaimName(userOperationClaimTypeEnum);
		if (operationClaim == null) {
			return new ErrorDataResult<OperationClaim>("Yetki bulunamadı");
		}
		return new SuccessDataResult<OperationClaim>(operationClaim);
	}

}
