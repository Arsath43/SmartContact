package com.smart.controller;

import javax.validation.Valid;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Mesaage;

import jakarta.servlet.http.HttpSession;


@Controller
public class Homecontroller {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
@Autowired
private UserRepository userrepository;
	
	
@RequestMapping("/")
public String home(Model model) {
	model.addAttribute("title","Home  - Smart Contact Manager");
	return "home";
		
	}
@RequestMapping("/about")
public String about(Model model) {
	model.addAttribute("title","About  - Smart Contact Manager");
	return "about";
		
	}

@RequestMapping("/signup/")
public String signup(Model model) {
	model.addAttribute("title","Register  - Smart Contact Manager");
	model.addAttribute("user", new User());
			return "signup";
		
	}


//handler
@RequestMapping(value= "/do_register",method=RequestMethod.POST)
public String registeruser(@Valid @ModelAttribute("user")User user,BindingResult result1 ,@RequestParam(value="agreement",defaultValue="false")boolean agreement,Model model,HttpSession session,@Valid RedirectAttributes redirectAttributes ) {
	try {
	if(!agreement) {
	
		System.out.println("you are not agreed terms and condition");
		throw new Exception("you are not agreed terms and condition");
	}
	
	if(result1.hasErrors()) {
	System.out.println("Error "+ result1.toString());
		model.addAttribute("user"+user);
	return "signup";	
	}
	user.setRole("ROLE_USER");
	user.setEnabled(true);
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	user.setImageurl("default.png");
	System.out.println("Agreement "+ agreement);
	
	System.out.println("User "+ user);
	
	User result = this.userrepository.save(user);
	model.addAttribute("user",  new User());
	
	redirectAttributes.addFlashAttribute("messages",new Mesaage("----Registered----","alert - success  !!"));
	return "signup";
	}
	catch(Exception e) {
//		e.printStackTrace();
//		model.addAttribute("User"+user);
		redirectAttributes.addFlashAttribute("messages",new Mesaage("something went wrong"+e.getMessage(),"alert --eror  !!"));
		return "signup";
	}
	
}


@GetMapping("/signin")
public String customLogin(Model model) {
	model.addAttribute("title","Login Page");
return "login"	;
}
}




