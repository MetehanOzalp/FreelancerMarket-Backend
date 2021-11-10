package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.UserOperationClaim;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface UserOperationClaimService {

	Result add(UserOperationClaim userOperationClaim);

	DataResult<List<UserOperationClaim>> getByUserId(int id);

}
