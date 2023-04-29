package ir.encoding.order.data.jpa.repositories.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ir.encoding.order.data.jpa.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o LEFT JOIN FETCH o.discounts WHERE o.id = :id ")
	Optional<Order> findById(@Param("id") Long id);
	
}
