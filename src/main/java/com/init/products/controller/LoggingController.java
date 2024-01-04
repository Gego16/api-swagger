package com.init.products.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.init.products.model.Logging;
import com.init.products.service.LoggingService;

@RestController
@RequestMapping("/logging")
public class LoggingController {

	@Autowired
	private LoggingService loggingService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Logging>>lista(){
		
		List<Logging> log = new ArrayList<>();
			try {
				log = loggingService.listausuarios();
				if(log != null) {
					return new ResponseEntity<>(log,HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}catch(Exception e){
				System.out.println(e);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
	}

	
	@PostMapping("/agregar")
	public ResponseEntity<Logging>agregar(@RequestBody Logging logging){
		Logging logSave;
		try {
			logSave = loggingService.save(logging);
			if(logSave != null) {
				return new ResponseEntity<>(logSave,HttpStatus.CREATED);
			}
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
		return null;
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Boolean>eliminar(@PathVariable Long id){
		try {
			loggingService.delete(id);
			return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
		}catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping("/editar/{id}")
	public ResponseEntity<Logging> editar(@PathVariable Long id, @RequestBody Logging logging) {
		
	    logging.setId(id); // Asigna el id al objeto Logging antes de editarlo
	    
	    Logging editado = loggingService.edit(logging);
	    
	    try {
	        if (editado != null) {
	            return new ResponseEntity<>(editado, HttpStatus.ACCEPTED);
	        } else {
	            return new ResponseEntity<>(editado, HttpStatus.NOT_ACCEPTABLE);
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}	
