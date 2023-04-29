package ir.encoding.order.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import ir.encoding.order.view.dto.user.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO findByUsername(String username);
	
}
