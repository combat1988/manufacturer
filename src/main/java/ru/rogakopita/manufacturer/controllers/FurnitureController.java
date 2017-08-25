package ru.rogakopita.manufacturer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rogakopita.manufacturer.dao.FurnitureDAO;
import ru.rogakopita.manufacturer.domain.Furniture;

@RestController
@RequestMapping(value = "/furniture")
public class FurnitureController extends AbstractCRUDController<Furniture> {
	
	@Autowired
	public FurnitureController(FurnitureDAO dao) {
		this.dao = dao;
	}
	
}
