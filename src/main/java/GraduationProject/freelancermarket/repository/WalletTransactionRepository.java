package GraduationProject.freelancermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import GraduationProject.freelancermarket.entities.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer> {

}
