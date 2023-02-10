package com.asraf.infosapp.model;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails{
	
	private User user;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	@Override
	public boolean isEnabled() {
		
		return false;
	}

}
