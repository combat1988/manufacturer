package ru.rogakopita.manufacturer.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.rogakopita.manufacturer.dao.EmployeeDAO;
import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.Order;
import ru.rogakopita.manufacturer.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends AbstractCRUDController<Employee> {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "{id}/orders", method=RequestMethod.GET)
	public @ResponseBody List<Order> getEmployeeOrders(@PathVariable long id) {
		return employeeService.getOrders(id);
	}
	
	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody Employee delete(@RequestBody Employee employee) throws Exception {
    	return employeeService.removeEmployee(employee);
    	
    }
	
}
