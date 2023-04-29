package ir.encoding.order.data.jpa.repositories.product.custom;

import java.util.List;

import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.view.dto.product.ProductSearchDTO;

public interface CustomProductRepository {

	List<Product> search(ProductSearchDTO criteria, int first, int size);

	Long count(ProductSearchDTO criteria);
	
	List<Product> findByIds(List<Long> ids);
}
