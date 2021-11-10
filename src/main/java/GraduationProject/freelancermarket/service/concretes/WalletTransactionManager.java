package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.entities.WalletTransaction;
import GraduationProject.freelancermarket.repository.WalletTransactionRepository;
import GraduationProject.freelancermarket.service.abstracts.WalletTransactionService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionManager implements WalletTransactionService {

	private final WalletTransactionRepository walletTransactionRepository;

	@Override
	public Result add(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
		return new SuccessResult("Cüzdan hareketi eklendi");
	}

	@Override
	public DataResult<List<WalletTransaction>> getByUserId(int userId) {
		return new SuccessDataResult<List<WalletTransaction>>(walletTransactionRepository.findByUserId(userId),
				"Kullanıcının cüzdan hareketleri listelendi");
	}

}
