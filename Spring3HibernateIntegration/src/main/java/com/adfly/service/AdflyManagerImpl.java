package com.adfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adfly.dao.AdflyDAO;
import com.adfly.entity.AdflyEntity;
import com.adfly.entity.User;
import com.adfly.entity.UserData;

@Service
public class AdflyManagerImpl implements AdflyManager {
	
	@Autowired
    private AdflyDAO adflyDAO;

	@Override
	@Transactional
	public void addEmployee(AdflyEntity employee) {
		adflyDAO.addEmployee(employee);
	}

	@Override
	@Transactional
	public List<AdflyEntity> getAllEmployees() {
		return adflyDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		adflyDAO.deleteEmployee(employeeId);
	}

	public void setEmployeeDAO(AdflyDAO employeeDAO) {
		this.adflyDAO = employeeDAO;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		this.adflyDAO.addUser(user);
		
	}

	@Override
	@Transactional
	public boolean verify(Integer userId) {
		return adflyDAO.verify(userId);
		
	}

	@Override
	public void addUserData(UserData userdata) {
		this.adflyDAO.addUserData(userdata);
		
	}
	
	
}
