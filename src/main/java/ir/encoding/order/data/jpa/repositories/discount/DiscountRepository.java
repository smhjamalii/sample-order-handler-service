package ir.encoding.order.data.jpa.repositories.discount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.encoding.order.data.jpa.entities.discount.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

	Discount findByCode(String code);	
	List<Discount> findByOrderId(Long orderId);
}
