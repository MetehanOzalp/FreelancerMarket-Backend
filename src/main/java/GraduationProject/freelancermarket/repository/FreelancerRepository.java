package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Freelancer;

public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {

}
