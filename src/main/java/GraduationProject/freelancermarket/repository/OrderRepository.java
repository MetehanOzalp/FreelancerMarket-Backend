package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
