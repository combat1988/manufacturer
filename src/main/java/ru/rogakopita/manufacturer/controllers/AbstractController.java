package ru.rogakopita.manufacturer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
	
	protected Logger log;
	
	 public AbstractController() {
	        log = LoggerFactory.getLogger(this.getClass());
	    }
	
		
//	protected static ObjectMapper mapper = new ObjectMapper();
	
//	public String jsonFromObject(Object object) {
//        StringWriter writer = new StringWriter();
//        try {
//            mapper.writeValue(writer, object);
//        } catch (Exception e) {
//            log.error("Unable to serialize to json: " + object, e);
//            return null;
//        }
//        return writer.toString();
//    }

}
