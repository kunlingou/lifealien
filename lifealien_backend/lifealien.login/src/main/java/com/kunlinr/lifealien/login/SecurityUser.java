package com.kunlinr.lifealien.login;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用于SpringSecurity登录的安全用户(UserDetails)
 * @author kunlingou
 */
public class SecurityUser extends User implements UserDetails{

	private static final long serialVersionUID = 1094545167511332283L;
	
	public SecurityUser(User user) {
		if(user != null) {
			this.setUserUuid(user.getUserUuid());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setEmail(user.getEmail());
            this.setTelephone(user.getTelephone());
            this.setRole(user.getRole());
            this.setImage(user.getImage());
            this.setLastIp(user.getLastIp());
            this.setLastTime(user.getLastTime());
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String username = this.getUsername();
		if(username != null) {
			authorities.add(new SimpleGrantedAuthority(username));
		}
		return authorities;
	}
	
	//账户是否未过期,过期无法验证
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//指定用户是否解锁,锁定的用户无法进行身份验证
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	//是否可用 ,禁用的用户不能身份验证
	@Override
	public boolean isEnabled() {
		return true;
	}

}
