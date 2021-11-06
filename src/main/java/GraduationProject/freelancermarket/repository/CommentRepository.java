package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
