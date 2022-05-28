package com.btl.qa.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.btl.qa.entity.User;

public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(User user) {
		this.user = user;
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		this.authorities = Arrays.asList(authority);
	}
	
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getId() {
		return user.getId();
	}
	public String getFullName() {
		return user.getFullName();
	}
	
	public String getAvatar() {
		return user.getAvatar();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (user.getStatus() == 0)
			return false;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO true-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setFullName(String fullName) {
		this.user.setFullName(fullName);
	}
	
	public void setAvatar(String avatar) {
		this.user.setAvatar(avatar);
	}
}
