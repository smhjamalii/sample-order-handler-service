package ir.encoding.order.data.jpa.entities.order;

import java.math.BigDecimal;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.types.order.OrderState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(schema = "order_service", name = "order_items")
@SequenceGenerator(name = "ORDER_ITEM_SEQ", initialValue = 10, allocationSize = 50)
@Getter @Setter @NoArgsConstructor
public class OrderItem extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ")
	private Long id;	

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	private Integer amount;
	
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private OrderState state;
}
