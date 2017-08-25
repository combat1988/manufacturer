package ru.rogakopita.manufacturer.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.rogakopita.manufacturer.dao.AbstractDAO;


public abstract class AbstractCRUDController<T> extends AbstractController {
	
	@Autowired
	protected AbstractDAO<T> dao;

	
	@RequestMapping(value = "/list")
    public @ResponseBody List<T> list() throws Exception {
        return dao.list();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody T read(@PathVariable("id") long id) throws Exception {
    	return dao.get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody T create(@RequestBody T data) throws Exception {
    	return dao.save(data);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody T update(@Valid @RequestBody T data) throws Exception {
    	return dao.update(data);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody T delete(@RequestBody T data) throws Exception {
    	dao.delete(dao.getByDTO(data).getId());
    	return data;
    	
    }
}
