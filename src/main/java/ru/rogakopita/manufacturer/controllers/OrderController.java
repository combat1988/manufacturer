package ru.rogakopita.manufacturer.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.rogakopita.manufacturer.dao.OrderDAO;
import ru.rogakopita.manufacturer.domain.Order;
import ru.rogakopita.manufacturer.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends AbstractCRUDController<Order> {

    @Autowired
    OrderService orderService;

    @Autowired
    public OrderController(OrderDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "{id}/timeToTerm", method = RequestMethod.GET)
    public @ResponseBody String timeToTerm(@PathVariable long id) {
        Map<String, Long> dateTimeToTerm = orderService.timeToTerm(id);
        return "Оталось " + dateTimeToTerm.get("day") + " дней и часов " + dateTimeToTerm.get("hour") + " до окончания заказа ";
    }

}
