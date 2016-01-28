package com.adfly.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adfly.entity.AdflyEntity;
import com.adfly.entity.User;
import com.adfly.entity.UserData;

@Repository
public class AdflyDaoImpl implements AdflyDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addEmployee(AdflyEntity employee) {
		this.sessionFactory.getCurrentSession().save(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdflyEntity> getAllEmployees() {
		return this.sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		AdflyEntity employee = (AdflyEntity) sessionFactory.getCurrentSession().load(
				AdflyEntity.class, employeeId);
        if (null != employee) {
        	this.sessionFactory.getCurrentSession().delete(employee);
        }
	}

	@Override
	public void addUser(User user) {
		user.setStatus("N");
		user.setCreateDate(getToday());
		System.out.println("USER ADDED SUCCESSFULLY====>"+this.sessionFactory.getCurrentSession().save(user));		
	}
	private String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		   String sdate=dateFormat.format(date);
		return (sdate);
	}

	@Override
	public boolean verify(Integer userId) {
		User user = (User) sessionFactory.getCurrentSession().load(
				User.class, userId);
		System.out.println("USER STATUS=====>"+user.getStatus());
        if (null != user && "N".equalsIgnoreCase(user.getStatus())) {
        	user.setStatus("Y");
        	this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        	return true;        	
        }
        return false;
		
	}

	@Override
	@Transactional
	public void addUserData(UserData userdata) {
		//userdata.setStatus("N");
		//userdata.setCreateDate(getToday());
		userdata.setStatus("1");
		System.out.println("USER ADDED SUCCESSFULLY====>"+this.sessionFactory.getCurrentSession().save(userdata));		
	
		
	}
	

}
