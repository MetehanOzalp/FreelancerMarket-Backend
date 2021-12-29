package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.entities.OperationClaim;
import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;
import GraduationProject.freelancermarket.utils.DataResult;

public interface OperationClaimService {

	DataResult<OperationClaim> getByOperationClaimType(UserOperationClaimTypeEnum userOperationClaimTypeEnum);

}
