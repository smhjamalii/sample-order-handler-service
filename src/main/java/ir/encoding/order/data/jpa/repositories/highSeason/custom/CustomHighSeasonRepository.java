package ir.encoding.order.data.jpa.repositories.highSeason.custom;

import java.util.List;

import ir.encoding.order.data.jpa.entities.highseason.HighSeason;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;

public interface CustomHighSeasonRepository {

	List<HighSeason> search(HighSeasonSearchDTO searchDto);
	
}
