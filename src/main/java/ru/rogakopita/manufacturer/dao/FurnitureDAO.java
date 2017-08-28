package ru.rogakopita.manufacturer.dao;

import org.springframework.stereotype.Component;

import ru.rogakopita.manufacturer.domain.Furniture;
import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@Component
public class FurnitureDAO extends AbstractDAO<Furniture> {

    public FurnitureDAO() {
        super(Furniture.class);
    }

    @Override
    public DTOIfc getByDTO(Furniture dto) {
        return dto;
    }

}
