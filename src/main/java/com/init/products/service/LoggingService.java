package com.init.products.service;

import java.util.List;

import com.init.products.model.Logging;

public interface LoggingService {
	
	public List<Logging>listausuarios();
	
	public Logging save(Logging logging);
	
	public Logging findById(Long id);
	
	public boolean delete(Long id);
	
	public Logging edit(Logging loggin);
	
}
