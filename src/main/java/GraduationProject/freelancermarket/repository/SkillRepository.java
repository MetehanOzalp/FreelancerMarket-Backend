package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

	List<Skill> findByFreelancerId(int id);

}
