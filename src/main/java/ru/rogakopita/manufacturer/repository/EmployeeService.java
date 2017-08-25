package ru.rogakopita.manufacturer.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rogakopita.manufacturer.dao.EmployeeDAO;
import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.Order;

@Service
public class EmployeeService {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	public List<Order> getOrders(long id) {
		return employeeDAO.get(id).getOrders();
	}
	
	public Employee removeEmployee(Employee employee) {
		employee = employeeDAO.get(employee.getId()); 
		if(orderService.distributeOrderOnDepartment(employee.getOrders())) {
			employeeDAO.delete(employee);
		};
		return employee;
	}
	
	
	

}
