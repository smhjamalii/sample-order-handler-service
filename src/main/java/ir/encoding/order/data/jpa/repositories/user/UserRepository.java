package ir.encoding.order.data.jpa.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ir.encoding.order.data.jpa.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.person WHERE u.username = :username")
	User findByUsername(@Param("username") String username);
	
}
