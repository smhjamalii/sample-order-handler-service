package ir.encoding.order.data.jpa.repositories.highSeason.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ir.encoding.order.data.jpa.entities.highseason.HighSeason;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class CustomHighSeasonRepositoryImpl implements CustomHighSeasonRepository {

	@PersistenceContext
	private EntityManager em;
	
	private static final String SEARCH_QUERY = "SELECT DISTINCT h FROM HighSeason h WHERE h.enabled = True ";
	
	@Override
	public List<HighSeason> search(HighSeasonSearchDTO searchDto) {
		StringBuilder jpqlQuery = new StringBuilder(SEARCH_QUERY);
		Map<String, Object> properties = new HashMap<>();
		if(searchDto.getBeginDateTime() != null) {
			jpqlQuery.append("AND h.beginDateTime <= :beginDateTime ");
			properties.put("beginDateTime", searchDto.getBeginDateTime());
		}
		if(searchDto.getEndDateTime() != null) {
			jpqlQuery.append("AND h.endDateTime >= :endDateTime ");
			properties.put("endDateTime", searchDto.getEndDateTime());			
		}
		
		if(searchDto.getCategories() != null && ! searchDto.getCategories().isEmpty() && searchDto.getProductIds() == null) {
			jpqlQuery.append("AND h.category IN :categories ");
			properties.put("categories", searchDto.getCategories());			
		} else if(searchDto.getProductIds() != null && ! searchDto.getProductIds().isEmpty() && searchDto.getCategories() == null) {
			jpqlQuery.append("AND h.product.id IN :productIds ");
			properties.put("productIds", searchDto.getProductIds());			
		} else {
			jpqlQuery.append("AND (h.category IN :categories OR h.product.id IN :productIds) ");
			properties.put("categories", searchDto.getCategories());
			properties.put("productIds", searchDto.getProductIds());
		}
		
		TypedQuery<HighSeason> typedQuery = em.createQuery(jpqlQuery.toString(), HighSeason.class);
		properties.entrySet().stream().forEach(entry -> typedQuery.setParameter(entry.getKey(), entry.getValue()));
		return typedQuery.getResultList();
	}

}
