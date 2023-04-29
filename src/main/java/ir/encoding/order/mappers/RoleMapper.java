package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.user.Role;
import ir.encoding.order.view.dto.user.RoleDTO;

public class RoleMapper {
	
	public static RoleDTO getDTO(Role role) {
		RoleDTO dto = RoleDTO.builder()
				.id(role.getId())
				.name(role.getName())
				.build();
		
		dto.setCreatedAt(role.getCreatedAt());
		dto.setEnabled(role.isEnabled());
		return dto;
	}
	
	public static Role getEntity(RoleDTO dto) {
		Role role = new Role();
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setCreatedAt(dto.getCreatedAt());
		role.setEnabled(dto.isEnabled());
		return role;
	}
	
}
