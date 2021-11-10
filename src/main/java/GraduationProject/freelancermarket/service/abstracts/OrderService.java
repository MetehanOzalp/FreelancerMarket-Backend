package GraduationProject.freelancermarket.service.abstracts;

import java.util.List;

import GraduationProject.freelancermarket.entities.Order;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.Result;

public interface OrderService {

	Result add(Order order);

	Result confirm(Order order);

	DataResult<List<Order>> getByEmployerId(int employerId);

	DataResult<List<Order>> getByFreelancerId(int freelancerId);

	DataResult<List<Order>> getAll();

}