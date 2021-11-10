package GraduationProject.freelancermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer> {

	List<WalletTransaction> findByUserId(int id);

}
