package ir.encoding.order.data.jpa.repositories.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ir.encoding.order.data.jpa.entities.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT o FROM OrderItem o WHERE o.order.id = :orderId")
	List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
	
}
