package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	Wallet findByUserId(int id);

}
