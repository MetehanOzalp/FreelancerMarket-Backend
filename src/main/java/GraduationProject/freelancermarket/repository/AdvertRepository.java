package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import GraduationProject.freelancermarket.entities.Advert;
import GraduationProject.freelancermarket.model.dto.AdvertSearchFilter;
import GraduationProject.freelancermarket.model.dto.AdvertFilter;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {

	@Query("Select j from GraduationProject.freelancermarket.entities.Advert j where"
			+ "((:#{#filter.term}) IS NULL OR j.title LIKE (%:#{#filter.term}%))"
			+ " and ((:#{#filter.slug}) IS NULL OR j.subCategoryId = (:#{#filter.slug}))"
			+ " and ((:#{#filter.minPrice}) IS NULL OR j.price >= (:#{#filter.minPrice}))"
			+ " and ((:#{#filter.maxPrice}) IS NULL OR j.price <= (:#{#filter.maxPrice}) OR (:#{#filter.maxPrice}) = 0.0)")
	Page<Advert> getBySearchFilter(@Param("filter") AdvertSearchFilter advertSearchFilter, Pageable pageable);

	@Query("Select j from GraduationProject.freelancermarket.entities.Advert j where"
			+ "((:#{#subCategoryName}) IS NULL OR j.subCategory.name LIKE (%:#{#subCategoryName}%))"
			+ " and ((:#{#filter.minPrice}) IS NULL OR j.price >= (:#{#filter.minPrice}))"
			+ " and ((:#{#filter.maxPrice}) IS NULL OR j.price <= (:#{#filter.maxPrice}) OR (:#{#filter.maxPrice}) = 0.0)")
	Page<Advert> getByFilter(@Param("filter") AdvertFilter advertFilter,
			@Param("subCategoryName") String subCategoryName, Pageable pageable);

	List<Advert> getByFreelancer_UserName(String userName);

	List<Advert> getByFreelancerId(int freelancerId);

	List<Advert> getBySubCategoryId(int subCategoryId);

	List<Advert> getByIdIn(List<Integer> ids);

}
