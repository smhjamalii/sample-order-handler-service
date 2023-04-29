package ir.encoding.order.data.jpa.repositories.highSeason;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.encoding.order.data.jpa.entities.highseason.HighSeason;
import ir.encoding.order.data.jpa.repositories.highSeason.custom.CustomHighSeasonRepository;

public interface HighSeasonRepository extends JpaRepository<HighSeason, Long>, CustomHighSeasonRepository {		
	
}
