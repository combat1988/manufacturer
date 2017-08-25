package ru.rogakopita.manufacturer.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rogakopita.manufacturer.dao.DepartmentDAO;
import ru.rogakopita.manufacturer.domain.Order;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentDAO departmentDAO;
	
	@Autowired 
	OrderService orderService;
	
	public List<Order> findAllOrders(long id) {
		return orderService.getOrdersByDepartment(departmentDAO.get(id));
	}

}
