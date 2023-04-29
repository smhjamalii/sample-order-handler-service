package ir.encoding.order.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.data.jpa.repositories.user.UserRepository;
import ir.encoding.order.mappers.UserMapper;
import ir.encoding.order.service.interfaces.UserService;
import ir.encoding.order.view.dto.user.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return UserMapper.getDTO(user);
	}

	@Override
	public UserDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return UserMapper.getDTO(user);
	}

}
