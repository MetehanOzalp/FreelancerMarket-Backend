package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.AdvertComment;

public interface AdvertCommentRepository extends JpaRepository<AdvertComment, Integer> {

	List<AdvertComment> findByAdvertId(int id);

}
