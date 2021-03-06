package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUserId(int id);

	List<Order> findByAdvert_FreelancerId(int id);

	List<Order> findByUser_UserName(String userName);

	List<Order> findByAdvert_Freelancer_UserName(String userName);

}
