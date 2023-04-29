package ir.encoding.order.mappers;

import ir.encoding.order.data.jpa.entities.person.Person;
import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.view.dto.person.PersonDTO;

public class PersonMapper {
	
	public static PersonDTO getDTO(Person person) {
		PersonDTO dto = PersonDTO.builder()
				.id(person.getId())
				.firstname(person.getFirstname())
				.lastname(person.getLastname())
				.phoneNumber(person.getPhoneNumber())
				.nationalCode(person.getNationalCode())
				.cardNumber(person.getCardNumber())
				.userId(person.getUser().getId())				
				.build();
		
		dto.setCreatedAt(person.getCreatedAt());
		dto.setEnabled(person.isEnabled());
		return dto;
	}
	
	public static Person getEntity(PersonDTO dto) {
		Person entity = new Person();
		entity.setId(dto.getId());
		entity.setFirstname(dto.getFirstname());
		entity.setLastname(dto.getLastname());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setNationalCode(dto.getNationalCode());
		entity.setCardNumber(dto.getCardNumber());
		entity.setUser(new User(dto.getUserId()));
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setEnabled(dto.isEnabled());
		return entity;
	}
	
}
