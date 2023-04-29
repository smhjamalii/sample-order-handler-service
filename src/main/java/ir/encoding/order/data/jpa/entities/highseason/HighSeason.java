package ir.encoding.order.data.jpa.entities.highseason;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.types.product.ProductCategory;
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
import lombok.Setter;

@Entity
@Table(schema = "order_service", name = "high_seasons")
@SequenceGenerator(name = "HIGH_SEASON_SEQ", initialValue = 10, allocationSize = 50)
@Getter @Setter
public class HighSeason extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIGH_SEASON_SEQ")
	private Long id;
	@Column(name = "begin_datatime", columnDefinition = "TIMESTAMP")
	private LocalDateTime beginDateTime;
	@Column(name = "end_datetime", columnDefinition = "TIMESTAMP")
	private LocalDateTime endDateTime;
	private ProductCategory category;
	private BigDecimal increaseAmount;
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
}
