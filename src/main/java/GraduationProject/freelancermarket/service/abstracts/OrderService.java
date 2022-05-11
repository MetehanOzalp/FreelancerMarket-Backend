package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Order;
import GraduationProject.freelancermarket.model.dto.OrderAddDto;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface OrderService {

	Result add(OrderAddDto orderAddDto);

	Result confirm(int id);

	DataResult<List<Order>> getByUserId(int userId);

	DataResult<List<Order>> getByUserName(String userName);

	DataResult<List<Order>> getByFreelancerId(int freelancerId);

	DataResult<List<Order>> getFreelancersOrdersByUserName(String userName);

	DataResult<List<Order>> getAll();

}
