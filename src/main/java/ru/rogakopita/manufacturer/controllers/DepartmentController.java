package ru.rogakopita.manufacturer.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.rogakopita.manufacturer.dao.DepartmentDAO;
import ru.rogakopita.manufacturer.domain.Department;
import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.Order;
import ru.rogakopita.manufacturer.repository.DepartmentService;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController extends AbstractCRUDController<Department> {
	
	@Autowired
	DepartmentService departmentService; 
	
	@Autowired
	public DepartmentController(DepartmentDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value= "/{id}/employees", method=RequestMethod.GET)
	public @ResponseBody List<Employee> getDepartmentEmployee(@PathVariable long id) {
		return dao.get(id).getEmployees();
	}
	
	@RequestMapping(value = "/{id}/orders")
	public @ResponseBody List<Order> getAllOrdersByDepartment(@PathVariable long id) {
		List<Order> orders = departmentService.findAllOrders(id);
		return orders;
	}
	
	

}
