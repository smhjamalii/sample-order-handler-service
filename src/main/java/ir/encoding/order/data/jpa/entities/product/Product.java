package ir.encoding.order.data.jpa.entities.product;

import java.math.BigDecimal;
import java.util.Objects;

import ir.encoding.order.data.jpa.entities.GenericEntity;
import ir.encoding.order.types.product.MeasurementType;
import ir.encoding.order.types.product.ProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "order_service", name = "products")
@SequenceGenerator(name = "PRODUCT_SEQ", initialValue = 10, allocationSize = 50)
@NamedQueries({
	@NamedQuery(
		name = "Product.findByIds",
		query = "SELECT p FROM Product p WHERE p.id IN :ids"
	)
})
@Getter @Setter @NoArgsConstructor
public class Product extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
	private Long id;
	private String name;
	private BigDecimal price;
	private String description;
	private String thumbnailImage;
	@Enumerated(EnumType.STRING)
	private MeasurementType measurementType;
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	private boolean fragile;
	
	public Product(Long id) {	
		this.id = id;
	}	
	
	public Product(Long productId, String productName, BigDecimal productPrice) {
		this.id = productId;
		this.name = productName;
		this.price = productPrice;
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(category, description, id, measurementType, name, price, thumbnailImage);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return category == other.category && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && measurementType == other.measurementType
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(thumbnailImage, other.thumbnailImage);
	}
		
}
