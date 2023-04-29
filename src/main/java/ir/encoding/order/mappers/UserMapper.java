package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.person.Person;
import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.view.dto.user.UserDTO;

public class UserMapper {
	
	public static UserDTO getDTO(User user) {
		if(user == null) return null;
		UserDTO dto = UserDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles().stream().map(RoleMapper::getDTO).toList())
				.personId(user.getPerson() != null ? user.getPerson().getId() : null)
				.build();
		
		dto.setCreatedAt(user.getCreatedAt());
		dto.setEnabled(user.isEnabled());
		return dto;
	}
	
	public static User getEntity(UserDTO dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRoles(dto.getRoles().stream().map(RoleMapper::getEntity).toList());
		user.setPerson(dto.getPersonId() != null ? new Person(dto.getPersonId()) : null);
		user.setCreatedAt(dto.getCreatedAt());
		user.setEnabled(dto.isEnabled());
		return user;
	}
	
}
