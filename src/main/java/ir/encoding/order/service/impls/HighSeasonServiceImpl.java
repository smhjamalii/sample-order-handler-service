package ir.encoding.order.service.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ir.encoding.order.data.jpa.entities.highseason.HighSeason;
import ir.encoding.order.data.jpa.repositories.highSeason.HighSeasonRepository;
import ir.encoding.order.mappers.HighSeasonMapper;
import ir.encoding.order.service.interfaces.HighSeasonService;
import ir.encoding.order.validation.groups.Edit;
import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class HighSeasonServiceImpl implements HighSeasonService {

	@Autowired
	private HighSeasonRepository highSeasonRepo;
	
	@Override
	public List<HighSeasonDTO> search(HighSeasonSearchDTO searchDto){
		List<HighSeason> highSeasons = highSeasonRepo.search(searchDto);
		return highSeasons.stream().map(HighSeasonMapper::getDTO).toList();
	}

	@Override
	public Optional<HighSeasonDTO> findById(@NotNull Long id) {
		Optional<HighSeason> entity = highSeasonRepo.findById(id);
		if(entity.isPresent()) {
			return Optional.of(HighSeasonMapper.getDTO(entity.get()));
		} else 
			return Optional.empty();
	}
	
	@Override
	public HighSeasonDTO save(@Valid HighSeasonDTO dto) {
		HighSeason highSeason = HighSeasonMapper.getEntity(dto);
		highSeason = highSeasonRepo.save(highSeason);
		dto.setId(highSeason.getId());
		return dto;
	}

	@Validated(Edit.class)
	@Override
	public HighSeasonDTO edit(@Valid HighSeasonDTO dto) {		
		HighSeason highSeason = HighSeasonMapper.getEntity(dto);
		highSeason = highSeasonRepo.save(highSeason);
		return HighSeasonMapper.getDTO(highSeason);
	}
	
	@Override
	public boolean delete(@NotNull Long id) {
		Optional<HighSeason> highSeason = highSeasonRepo.findById(id);
		if(highSeason.isPresent()) {
			highSeasonRepo.delete(highSeason.get());
			return true;
		} else
			return false;
	}

	@Override
	public void deleteAll() {
		List<HighSeason> allRecords = highSeasonRepo.findAll(); 
		highSeasonRepo.deleteAll(allRecords);
	}
	
}
