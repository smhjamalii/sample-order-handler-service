package ir.encoding.order.data.jpa.entities.user;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(schema = "order_service", name = "roles")
@SequenceGenerator(name = "ROLE_SEQ", initialValue = 10, allocationSize = 5)
@Getter @Setter @NoArgsConstructor
public class Role extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    private Long id;
	private String name;
	
}
