package ir.encoding.order.data.jpa.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.data.jpa.repositories.product.custom.CustomProductRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {	
	
}
