package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.UserOperationClaim;

public interface UserOperationClaimRepository extends JpaRepository<UserOperationClaim, Integer> {

	List<UserOperationClaim> findByUserId(int id);

}
