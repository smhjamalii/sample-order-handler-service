package ir.encoding.order.data.jpa.entities.order;

import java.time.LocalDateTime;
import java.util.List;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.discount.Discount;
import ir.encoding.order.data.jpa.entities.user.User;
import ir.encoding.order.types.order.OrderState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "order_service", name = "orders")
@SequenceGenerator(name = "ORDER_SEQ", initialValue = 10, allocationSize = 50)
@Getter @Setter @NoArgsConstructor
public class Order extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
	private Long id;	
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<OrderItem> items;
	
	@Enumerated(EnumType.STRING)
	private OrderState state;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Discount> discounts;
	
	@Column(name = "registration_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime registrationDateTme;
	
	@Column(name = "modify_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime modifyDateTme;

	public Order(Long id) {		
		this.id = id;
	}
		
}
