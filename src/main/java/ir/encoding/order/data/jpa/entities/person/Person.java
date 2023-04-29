package ir.encoding.order.data.jpa.entities.person;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*
* @author S M Hossein Jamali
*/
@Entity
@Table(schema = "order_service", name = "person")
@SequenceGenerator(name = "PERSON_SEQ", initialValue = 10, allocationSize = 5)
@Getter @Setter @NoArgsConstructor
public class Person extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    private Long id;
    
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String nationalCode;
	private String cardNumber;
	
	@OneToOne(mappedBy = "person")
	private User user;

	public Person(Long id) {		
		this.id = id;
	}
	
}
