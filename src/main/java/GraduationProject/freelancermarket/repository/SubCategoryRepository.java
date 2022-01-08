package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

	List<SubCategory> findByTopCategoryId(int id);

	List<SubCategory> findByTopCategory_Name(String name);

}
