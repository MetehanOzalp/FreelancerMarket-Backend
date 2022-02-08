package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.WalletTransaction;
import GraduationProject.freelancermarket.repository.WalletTransactionRepository;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.service.abstracts.WalletTransactionService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionManager implements WalletTransactionService {

	private final WalletTransactionRepository walletTransactionRepository;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;

	@Override
	public Result add(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
		return new SuccessResult("Cüzdan hareketi eklendi");
	}

	@Override
	public DataResult<List<WalletTransaction>> getByUserId(int userId) {
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(userId));
		if (businessRules != null) {
			return new ErrorDataResult<List<WalletTransaction>>(businessRules.getMessage());
		}
		return new SuccessDataResult<List<WalletTransaction>>(walletTransactionRepository.findByUserId(userId),
				"Kullanıcının cüzdan hareketleri listelendi");
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
