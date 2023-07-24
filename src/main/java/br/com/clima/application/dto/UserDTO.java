package br.com.clima.application.dto;

import br.com.clima.application.domain.model.cie.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	public enum Role {USER, ADMIN}
	
	private final Users user;

	public UserDTO(Users user) {
		this.user = user;
    }
	
	@SuppressWarnings("unused")
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	    final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
	    Set<Role> roles = null;
	
	    if (roles != null) {
	        for (Role role : roles) {
		        authorities.add(new SimpleGrantedAuthority(role.name()));
		    }
	    }
	    return authorities;
    }
	
	public Users getUser() {
        return user;
    }
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
}
