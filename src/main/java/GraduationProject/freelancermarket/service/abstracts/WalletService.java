package GraduationProject.freelancermarket.service.abstracts;

import GraduationProject.freelancermarket.entities.Wallet;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface WalletService {

	Result add(Wallet wallet);

	Result moneyWithdraw(int userId, Double amount);

	Result moneyDeposit(int userId, Double amount);

	DataResult<Wallet> getByUserId(int userId);

}
