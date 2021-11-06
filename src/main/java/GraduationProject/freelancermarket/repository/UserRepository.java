package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
