/**
 * 
 */
package com.ffg.rrn.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffg.rrn.dao.ServiceCoordinatorDAO;
import com.ffg.rrn.model.ServiceCoordinator;

/**
 * @author FFGRRNTeam
 *
 */
@Service
public class ServiceCoordinatorServiceImpl {

	@Autowired
	private ServiceCoordinatorDAO serviceCoordinatorDao;
	
	public List<ServiceCoordinator> getAllServiceCoordinators() {
		return this.serviceCoordinatorDao.getAllServiceCoordinators();
	}

	public Long saveServiceCoordinator(final ServiceCoordinator sc) {
		return serviceCoordinatorDao.saveServiceCoordinator(sc);
	}

}
