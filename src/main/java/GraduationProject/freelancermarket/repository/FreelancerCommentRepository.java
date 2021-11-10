package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.FreelancerComment;

public interface FreelancerCommentRepository extends JpaRepository<FreelancerComment, Integer> {

	List<FreelancerComment> findByFreelancerId(int id);

}
