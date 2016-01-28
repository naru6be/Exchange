package com.adfly.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adfly.entity.AdflyEntity;
import com.adfly.entity.User;
import com.adfly.entity.UserData;
import com.adfly.service.AdflyManager;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;

@Controller
public class AdflyController {

	@Autowired
	private AdflyManager adflyManager;
	
	public void setEmployeeManager(AdflyManager adflyManager) {
		this.adflyManager = adflyManager;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/addLinks";
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEmployees(ModelMap map) {
		
		map.addAttribute("employee", new AdflyEntity());
		map.addAttribute("employeeList", adflyManager.getAllEmployees());

		return "editEmployeeList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployee(
			@ModelAttribute(value = "employee") AdflyEntity employee,
			BindingResult result) {
		adflyManager.addEmployee(employee);
		return "redirect:/list";
	}

	@RequestMapping("/delete/{adflyId}")
	public String deleteEmplyee(@PathVariable("adflyId") Integer adflyId) {
		adflyManager.deleteEmployee(adflyId);
		return "redirect:/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		System.out.print(model.get("success"));
		return "register";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(ModelMap model) {
		model.addAttribute("user", new User());
		return "success";
	}

	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value="user") User user,BindingResult bind,ModelMap model){
		System.out.print("i am in register");
		if(bind.hasErrors()){			
			return "redirect:/register";
		}					
		adflyManager.addUser(user);
		model.addAttribute("user", user);		
		return "redirect:/success";
	
    }
	
	@RequestMapping("/verify/{userId}")
	public String verify(@PathVariable("userId") Integer userId,ModelMap model,Model mod) {
		System.out.println("USER ID======>"+userId);
		boolean result=adflyManager.verify(userId);
		System.out.println("Result======>"+result);
		if(result){
			model.addAttribute("success", "User activated successfully");
			return "redirect:/success";
		}
		mod.addAttribute("success", "User activated already");
		//model.addAttribute("success", "User activated already");
		return "redirect:/register";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(ModelMap model) {
		model.addAttribute("userdata", new UserData());
		System.out.print(model.get("success"));
		return "signup";
	}
	
	@RequestMapping(value = "/addUserData", method = RequestMethod.POST)
	public String addUserData(@ModelAttribute(value="userdata") UserData userdata,BindingResult bind,ModelMap model){
		System.out.print("i am in signup");
		if(bind.hasErrors()){			
			return "redirect:/register";
		}					
		adflyManager.addUserData(userdata);
		model.addAttribute("userdata", userdata);		
		return "redirect:/success";
	
    }
	
	@RequestMapping(value = "/addLinks", method = RequestMethod.POST)
	public String addLinks(@ModelAttribute(value="user") User userdata,ModelMap map){
	
		map.addAttribute("employee", new AdflyEntity());
		map.addAttribute("employeeList", adflyManager.getAllEmployees());

		return "editEmployeeList";

		
	}
}
