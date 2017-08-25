package ru.rogakopita.manufacturer.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rogakopita.manufacturer.dao.OrderDAO;
import ru.rogakopita.manufacturer.domain.Department;
import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	public Order createOrder(Order order) {
		return orderDAO.save(order);
	}
	
	public List<Order> getAll() {
		return orderDAO.list();
	}
	
	public List<Order> getOrdersByDepartment(Department department)
	{
		return orderDAO.getOrders(department);
	}
	
	public List<Order> getOrdersByEmployee(Employee employee) {
		return orderDAO.getOrders(employee);
	}
	
	public List<Order> getActiveOrders() {
		return orderDAO.getActiveOrders();
	}
	
	public boolean distributeOrderOnDepartment(List<Order> orders) {
		for (Order order : orders) {
			order.setProducer(null);
			orderDAO.update(order);
		}
		return true;
	}
	
	public Map<String, Long> timeToTerm(long id) {
		Date termTime = orderDAO.get(id).getTermTime();
		LocalDate ld = LocalDate.now();
		LocalDate td = Instant.ofEpochMilli(termTime.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		
		Map<String, Long> map = new HashMap<>();
		map.put("day", td.getLong(ChronoField.CLOCK_HOUR_OF_DAY)-ld.getLong(ChronoField.CLOCK_HOUR_OF_DAY)/24);
		map.put("hour", td.getLong(ChronoField.CLOCK_HOUR_OF_DAY)-ld.getLong(ChronoField.CLOCK_HOUR_OF_DAY)%24);
		return map; 
		
	}
	
}
