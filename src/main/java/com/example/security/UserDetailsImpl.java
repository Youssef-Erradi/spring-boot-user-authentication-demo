package com.example.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 8507243922193357582L;
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole().toString()),
				new SimpleGrantedAuthority("Role_" + user.getRole().toString()));
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
		return true;
	}

}
