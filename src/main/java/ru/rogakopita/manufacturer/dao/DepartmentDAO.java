package ru.rogakopita.manufacturer.dao;

import org.springframework.stereotype.Component;

import ru.rogakopita.manufacturer.domain.Department;
import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@Component
public class DepartmentDAO extends AbstractDAO<Department> {

    public DepartmentDAO() {
        super(Department.class);
    }

    @Override
    public DTOIfc getByDTO(Department dto) {
        return dto;
    }

}
