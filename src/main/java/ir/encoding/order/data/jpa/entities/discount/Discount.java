package ir.encoding.order.data.jpa.entities.discount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.order.Order;
import ir.encoding.order.data.jpa.entities.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "order_service", name = "discounts")
@SequenceGenerator(name = "DISCOUNT_SEQ", initialValue = 10, allocationSize = 50)
@Getter @Setter @NoArgsConstructor
public class Discount extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCOUNT_SEQ")
	private Long id;	
	
	private String code;
	
	@Column(name = "validation_begin_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime validationBeginDateTime;
	
	@Column(name = "validation_ednd_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime validationEndDateTime;
	
	private BigDecimal price;
	
	private BigDecimal maximumPrice;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;	
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
}
