package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
