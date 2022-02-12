package GraduationProject.freelancermarket.service.concretes;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Order;
import GraduationProject.freelancermarket.model.dto.OrderAddDto;
import GraduationProject.freelancermarket.repository.OrderRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.service.abstracts.OrderService;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.service.abstracts.WalletService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {

	private final OrderRepository orderRepository;
	private final WalletService walletService;
	private final AdvertService advertService;
	private final TokenUserNameAndIdValidationService tokenUserNameAndIdValidationService;
	private final ModelMapper modelMapper;

	@Override
	public Result add(OrderAddDto orderAddDto) {
		var result = BusinessRules.run(userIdAndTokenUserNameVerification(orderAddDto.getEmployerId()),
				checkIfEnoughBalance(orderAddDto.getEmployerId(), orderAddDto.getAdvertId()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Order order = modelMapper.map(orderAddDto, Order.class);
		order.setStatus(false);
		order.setCreatedDate(LocalDate.now());
		orderRepository.save(order);
		var withdraw = BusinessRules.run(moneyWithdraw(order.getUserId(), order.getAdvertId()));
		if (withdraw != null) {
			return new ErrorResult(withdraw.getMessage());
		}
		return new SuccessResult("Sipariş eklendi");
	}

	@Override
	public Result confirm(int id) {
		var order = orderRepository.findById(id).orElse(null);
		if (order == null) {
			return new ErrorResult("Sipariş bulunamadı");
		}
		if (order.isStatus()) {
			return new ErrorResult("Sipariş zaten onaylanmış");
		}
		var businessRules = BusinessRules.run(userIdAndTokenUserNameVerification(order.getUserId()),
				moneyDeposit(order.getAdvert().getFreelancerId(), order.getAdvert().getPrice()));
		if (businessRules != null) {
			return new ErrorResult(businessRules.getMessage());
		}
		order.setStatus(true);
		orderRepository.save(order);
		return new SuccessResult("İş onaylandı");
	}

	@Override
	public DataResult<List<Order>> getByUserId(int userId) {
		return new SuccessDataResult<List<Order>>(orderRepository.findByUserId(userId),
				"İşverenin siparişleri listelendi");
	}

	@Override
	public DataResult<List<Order>> getByFreelancerId(int freelancerId) {
		return new SuccessDataResult<List<Order>>(orderRepository.findByAdvert_FreelancerId(freelancerId),
				"Freelancerların siparişleri listelendi");
	}

	@Override
	public DataResult<List<Order>> getAll() {
		return new SuccessDataResult<List<Order>>(orderRepository.findAll(), "Siparişler listelendi");
	}

	public Result checkIfEnoughBalance(int userId, int advertId) {
		var wallet = walletService.getByUserId(userId);
		if (!wallet.isSuccess()) {
			return new ErrorResult(wallet.getMessage());
		}
		var advert = advertService.getById(advertId);
		if (!advert.isSuccess()) {
			return new ErrorResult(wallet.getMessage());
		}
		if (wallet.getData().getBalance() < advert.getData().getPrice()) {
			return new ErrorResult("Yetersiz bakiye");
		}
		return new SuccessResult();
	}

	public Result moneyWithdraw(int userId, int advertId) {
		var advert = advertService.getById(advertId);
		if (!advert.isSuccess()) {
			return new ErrorResult(advert.getMessage());
		}
		var withdraw = walletService.moneyWithdraw(userId, advert.getData().getPrice());
		if (!withdraw.isSuccess()) {
			return new ErrorResult(withdraw.getMessage());
		}
		return new SuccessResult();
	}

	public Result moneyDeposit(int userId, Double amount) {
		var deposit = walletService.moneyDeposit(userId, amount);
		if (!deposit.isSuccess()) {
			return new ErrorResult(deposit.getMessage());
		}
		return new SuccessResult();
	}

	public Result userIdAndTokenUserNameVerification(int userId) {
		var result = tokenUserNameAndIdValidationService.userIdAndTokenUserNameVerification(userId);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		return new SuccessResult();
	}

}
