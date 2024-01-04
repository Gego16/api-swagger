package com.init.products.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.products.model.Logging;
import com.init.products.model.dao.LoggingDao;
import com.init.products.service.LoggingService;

@Service
public class LoggingServiceImpl implements LoggingService{
	
	@Autowired
	private LoggingDao loggingDao;

	@Override
	public List<Logging> listausuarios() {
		return (List<Logging>) loggingDao.findAll();
	}

	@Override
	public Logging save(Logging logging) {
		return loggingDao.save(logging);
	}

	@Override
	public Logging findById(Long id) {
		return loggingDao.findById(id).orElse(null);
	}

	@Override
	public boolean delete(Long id) {
		Logging log = findById(id);
		loggingDao.deleteById(log.getId());
		return true;
	}


	@Override
	public Logging edit(Logging logging) {
		Logging editar = findById(logging.getId());
		editar.setNomUsuario(logging.getNomUsuario());;
		editar.setApPaterno(logging.getApPaterno());
		editar.setApMaterno(logging.getApMaterno());
		save(editar);
		return editar;
	}
}
