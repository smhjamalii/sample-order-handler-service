package ir.encoding.order.service.interfaces;

import java.util.List;
import java.util.Optional;

import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface HighSeasonService {

	List<HighSeasonDTO> search(HighSeasonSearchDTO searchDto);
			
	HighSeasonDTO save(@Valid HighSeasonDTO dto);
	HighSeasonDTO edit(@Valid HighSeasonDTO dto);
	boolean delete(@NotNull Long id);

	Optional<HighSeasonDTO> findById(@NotNull Long id);

	void deleteAll();
}
