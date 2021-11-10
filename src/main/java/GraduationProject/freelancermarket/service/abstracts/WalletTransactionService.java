package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.WalletTransaction;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface WalletTransactionService {

	Result add(WalletTransaction walletTransaction);

	DataResult<List<WalletTransaction>> getByUserId(int userId);

}
