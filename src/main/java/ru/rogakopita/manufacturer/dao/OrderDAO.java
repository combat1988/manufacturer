package ru.rogakopita.manufacturer.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import ru.rogakopita.manufacturer.domain.Department;
import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.Order;
import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@Component
public class OrderDAO extends AbstractDAO<Order> {

    public OrderDAO() {
        super(Order.class);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrders(Department department) {
        return currentSession().createCriteria(entityClass)
                .createAlias("furniture", "fur")
                .add(Restrictions.eq("fur.department", department))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrders(Employee employee) {
        return currentSession().createCriteria(entityClass)
                .add(Restrictions.eq("producer", employee))
                .list();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getActiveOrders() {
        return currentSession().createCriteria(entityClass)
                .add(Restrictions.lt("termTime", new Date()))
                .list();
    }

    @Override
    public DTOIfc getByDTO(Order dto) {
        return dto;
    }

}
