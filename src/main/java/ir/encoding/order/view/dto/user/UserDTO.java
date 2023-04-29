package ir.encoding.order.view.dto.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.view.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author S M Hossein Jamali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserDTO extends DTO implements UserDetails {
    
    private Long id;
    private String username;
    private String password;
    private List<RoleDTO> roles;    
    private Long personId;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return super.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return super.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}    
    
}
