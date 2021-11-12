package GraduationProject.freelancermarket.service.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.business.BusinessRules;
import GraduationProject.freelancermarket.entities.Order;
import GraduationProject.freelancermarket.model.dto.OrderAddDto;
import GraduationProject.freelancermarket.repository.OrderRepository;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.service.abstracts.EmployerService;
import GraduationProject.freelancermarket.service.abstracts.FreelancerService;
import GraduationProject.freelancermarket.service.abstracts.OrderService;
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
	private final EmployerService employerService;
	private final FreelancerService freelancerService;
	private final WalletService walletService;
	private final AdvertService advertService;
	private final ModelMapper modelMapper;

	@Override
	public Result add(OrderAddDto orderAddDto) {
		var employer = employerService.getById(orderAddDto.getEmployerId());
		if (employer.getData() == null) {
			return new ErrorResult("İşveren bulunamadı");
		}
		var result = BusinessRules.run(checkIfEnoughBalance(orderAddDto.getEmployerId(), orderAddDto.getAdvertId()));
		if (result != null) {
			return new ErrorResult(result.getMessage());
		}
		Order order = modelMapper.map(orderAddDto, Order.class);
		order.setStatus(false);
		orderRepository.save(order);
		var withdraw = walletService.moneyWithdraw(order.getEmployerId(), order.getAdvert().getPrice());
		if (!withdraw.isSuccess()) {
			return new ErrorResult(withdraw.getMessage());
		}
		return new SuccessResult("Sipariş eklendi");
	}

	@Override
	public Result confirm(Order order) {
		var employer = employerService.getById(order.getEmployerId());
		if (employer.getData() == null) {
			return new ErrorResult("İşveren bulunamadı");
		}
		var freelancer = freelancerService.getById(order.getAdvert().getFreelancerId());
		if (freelancer.getData() == null) {
			return new ErrorResult("Freelancer bulunamadı");
		}
		order.setStatus(true);
		orderRepository.save(order);
		var deposit = walletService.moneyDeposit(order.getAdvert().getFreelancerId(), order.getAdvert().getPrice());
		if (!deposit.isSuccess()) {
			return new ErrorResult(deposit.getMessage());
		}
		return new SuccessResult("İş onaylandı");
	}

	@Override
	public DataResult<List<Order>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<Order>>(orderRepository.findByEmployerId(employerId),
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
		if (wallet.getData() == null || !wallet.isSuccess()) {
			return new ErrorResult(wallet.getMessage());
		}
		var advert = advertService.getById(advertId);
		if (advert.getData() == null || !advert.isSuccess()) {
			return new ErrorResult(wallet.getMessage());
		}
		if (wallet.getData().getBalance() < advert.getData().getPrice()) {
			return new ErrorResult("Yetersiz bakiye");
		}
		return new SuccessResult();
	}

}
