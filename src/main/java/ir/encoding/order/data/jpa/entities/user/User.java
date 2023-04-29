package ir.encoding.order.data.jpa.entities.user;

import java.util.List;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(schema = "order_service", name = "users")
@SequenceGenerator(name = "USER_SEQ", initialValue = 10, allocationSize = 5)
@Getter @Setter @NoArgsConstructor
public class User extends GenericEntity {   

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    private Long id;
    
    private String username;
    private String password;
    
    @ManyToMany
    @JoinTable(		 
    	name = "USER_ROLES",
		joinColumns = {@JoinColumn(name = "USER_ID")},
		inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
	)
    private List<Role> roles;
    
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    
    public User(Long id) {
		this.id = id;
	}    
    
}
