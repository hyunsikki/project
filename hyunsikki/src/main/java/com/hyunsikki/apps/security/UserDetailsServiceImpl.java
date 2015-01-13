package com.hyunsikki.apps.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hyunsikki.apps.ui.front.mapper.UserMapper;
import com.hyunsikki.apps.ui.front.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		CustomUserDetils customUserDetils = new CustomUserDetils();
		
		User user	= new User();
		user		= userMapper.getUser(userId);
		
		log.debug("##########################################################################");
		log.debug("Access User ID :: [[ {} ]]");
		log.debug("##########################################################################");
		
		if(user !=null){
			customUserDetils.setUsername(user.getUserId());
			customUserDetils.setPassword(user.getUserPasswd());
			customUserDetils.setNickName(user.getUserName());
			customUserDetils.setAuthorities(getAuthorities(user.getLevel()));
		}
		return customUserDetils;
	}

	private Collection<GrantedAuthority> getAuthorities(int level){
		// Create a list of grants for this user 
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		
		if(level == User.LEVEL_ADMIN)
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN")); 
		return authList;
	}
}
