package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.OperationClaim;
import GraduationProject.freelancermarket.model.enums.UserOperationClaimTypeEnum;

public interface OperationClaimRepository extends JpaRepository<OperationClaim, Integer> {

	OperationClaim findByClaimName(UserOperationClaimTypeEnum operationClaimTypeEnum);

}
