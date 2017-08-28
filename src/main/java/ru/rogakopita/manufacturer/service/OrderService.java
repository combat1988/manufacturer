package ru.rogakopita.manufacturer.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
        LocalDateTime ld = LocalDateTime.now();
        LocalDateTime td = LocalDateTime.ofInstant(Instant.ofEpochMilli(termTime.getTime()), ZoneId.systemDefault());
        long diffDays = Period.between(ld.toLocalDate(), td.toLocalDate()).getDays();
        Map<String, Long> map = new HashMap<>();
        map.put("day", diffDays);
        map.put("hour", ChronoUnit.HOURS.between(ld, td) - diffDays * 24);
        return map;

    }
}
