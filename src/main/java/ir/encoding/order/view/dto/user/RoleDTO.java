package ir.encoding.order.view.dto.user;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.view.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class RoleDTO extends DTO implements GrantedAuthority {

	private Long id;
	private String name;
	
	@Override
	public String getAuthority() {		
		return name;
	}

}
