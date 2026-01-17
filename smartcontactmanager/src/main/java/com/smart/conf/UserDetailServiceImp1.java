package com.smart.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class UserDetailServiceImp1 implements UserDetailsService{

	@Autowired
	private UserRepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userrepository.getUserByUserName(username);
		
		if (user==null) {
			throw new UsernameNotFoundException("Could not Found user error");
		}
		CustomerUserDetail customuserdetail = new CustomerUserDetail(user);
		
		return customuserdetail;
	}

}
