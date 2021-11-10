package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Wallet;
import GraduationProject.freelancermarket.entities.WalletTransaction;
import GraduationProject.freelancermarket.model.enums.WalletTransactionTypeEnum;
import GraduationProject.freelancermarket.repository.WalletRepository;
import GraduationProject.freelancermarket.service.abstracts.WalletService;
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
public class WalletManager implements WalletService {

	private final WalletRepository walletRepository;
	private final WalletTransactionService walletTransactionService;

	@Override
	public Result add(Wallet wallet) {
		walletRepository.save(wallet);
		return new SuccessResult("Cüzdan eklendi");
	}

	@Override
	public Result moneyWithdraw(int userId, Double amount) {
		var wallet = walletRepository.findByUserId(userId);
		if (wallet == null) {
			return new ErrorResult("Cüzdan bulunamadı");
		}
		var result = BusinessRules.run(checkIfEnoughBalance(wallet.getBalance(), amount));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		var newBalance = wallet.getBalance() - amount;
		wallet.setBalance(newBalance);
		walletRepository.save(wallet);
		WalletTransaction walletTransaction = WalletTransaction.builder().userId(userId)
				.transactionName(WalletTransactionTypeEnum.MONEY_WITHDRAW).amount(amount).date(LocalDate.now()).build();
		walletTransactionService.add(walletTransaction);
		return new SuccessResult("Cüzdandan para çekildi");
	}

	@Override
	public Result moneyDeposit(int userId, Double amount) {
		var wallet = walletRepository.findByUserId(userId);
		if (wallet == null) {
			return new ErrorResult("Cüzdan bulunamadı");
		}
		var newBalance = wallet.getBalance() + amount;
		wallet.setBalance(newBalance);
		walletRepository.save(wallet);
		WalletTransaction walletTransaction = WalletTransaction.builder().userId(userId)
				.transactionName(WalletTransactionTypeEnum.MONEY_DEPOSIT).amount(amount).date(LocalDate.now()).build();
		walletTransactionService.add(walletTransaction);
		return new SuccessResult("Cüzdana para yüklendi");
	}

	@Override
	public DataResult<Wallet> getByUserId(int userId) {
		var wallet = walletRepository.findByUserId(userId);
		if (wallet == null) {
			return new ErrorDataResult<Wallet>("Kullanıcıya ait cüzdan bulunamadı");
		}
		return new SuccessDataResult<Wallet>(wallet, "Kullanıcının cüzdanı listelendi");
	}

	public Result checkIfEnoughBalance(Double balance, Double amount) {
		if (balance < amount) {
			return new ErrorResult("Bakiye yetersiz");
		}
		return new SuccessResult();
	}

}
