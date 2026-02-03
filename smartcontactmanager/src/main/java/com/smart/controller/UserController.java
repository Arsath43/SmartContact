package com.smart.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.entities.contact;
import com.smart.helper.Mesaage;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userrepository;
	
	
	//method for adding common data response
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
		String username = principal.getName();
		System.out.println("USERNAME  "+ username);
		 
	User user =	userrepository.getUserByUserName(username);
//		
	System.out.println("USER"+user);
//	

	
	model.addAttribute("user",user);
	}
	
	
	// Home Dashboard
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal){
		model.addAttribute("title", "User Dashboard");
		
	
		return "normal/user_dashboard";
	}
	@GetMapping("/test")
	public String test() {
	    return "normal/test";
	}
	
	
	//add form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new contact());
		
		
		return "normal/add_contact_form";
		
	}
	
	//process add contact form
   @PostMapping("/process-contact")
   public String processcontact(@ModelAttribute contact contact,
		   @RequestParam("profileImage") MultipartFile file,Principal principal, RedirectAttributes redirectAttributes) {
	
	   
	   try {
	   String name = principal.getName();//getusername
		
	   User user =	userrepository.getUserByUserName(name);
        contact.setUser(user);
        
        //process and upload file
        
       
        if(file.isEmpty()) {
        
        	System.out.println("File is Empty");
        	
        	
            
        }
        else {
        contact.setImage(file.getOriginalFilename());
        
        File savefile = new ClassPathResource("static/img").getFile();
      
      Path path =  Paths.get(savefile.getAbsolutePath()+File.separator+file.getOriginalFilename());
       
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        
        System.out.println("Image Uploaded");
        
        }
        
        
	   user.getContacts().add(contact);
	   this.userrepository.save(user);
	   
	   
	   System.out.println("data"+contact);
	   System.out.println("added to database");
	  
	   redirectAttributes.addFlashAttribute("message", new Mesaage("your contact is added !!","success"));
	   
	   }
	   catch(Exception e) {
		   System.out.println("error"+e.getMessage());
		   e.printStackTrace();
		  //message error 
		   redirectAttributes.addFlashAttribute("message", new Mesaage("Some went wrong !!","danger"));
		   
	   }
	   return "redirect:/user/add-contact";   
   }
}
