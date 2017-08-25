package ru.rogakopita.manufacturer.dao;

import org.springframework.stereotype.Component;

import ru.rogakopita.manufacturer.domain.Employee;
import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@Component
public class EmployeeDAO extends AbstractDAO<Employee> {
	
	public EmployeeDAO() {
		setEntityClass(Employee.class);
	}

	@Override
	public DTOIfc getByDTO(Employee dto) {
		return dto;
	}

}
