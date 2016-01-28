package com.adfly.service;

import java.util.List;

import com.adfly.entity.AdflyEntity;
import com.adfly.entity.User;
import com.adfly.entity.UserData;

public interface AdflyManager {
	public void addEmployee(AdflyEntity employee);
    public List<AdflyEntity> getAllEmployees();
    public void deleteEmployee(Integer employeeId);
    public void addUser(User user);
	public boolean verify(Integer userId);
	public void addUserData(UserData userdata);
}
