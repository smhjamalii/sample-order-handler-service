package ir.encoding.order.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.encoding.order.configs.security.JwtUtils;
import ir.encoding.order.service.interfaces.UserService;
import ir.encoding.order.view.dto.user.UserDTO;

@RestController
@RequestMapping(path = "/tokens", produces = OrderServiceMediaTypes.V1)
public class AuthResource {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/access-token")
	public ResponseEntity<String> accessToken(
			@RequestHeader("username") String username, 
			@RequestHeader("password") String password) {
		
		UserDTO userDto = userService.findByUsername(username);
		if(userDto != null && passwordEncoder.matches(password, userDto.getPassword())) {
			String jwt = jwtUtils.getToken(username, userDto.getRoles());			
			return ResponseEntity.of(Optional.of(jwt));
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
}
