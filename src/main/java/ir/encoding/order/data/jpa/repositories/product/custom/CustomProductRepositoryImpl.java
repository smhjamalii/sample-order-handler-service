package ir.encoding.order.data.jpa.repositories.product.custom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.view.dto.product.ProductSearchDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class CustomProductRepositoryImpl implements CustomProductRepository {
	
	@PersistenceContext
	private EntityManager em;

	private static final String SEARCH_QUERY = "SELECT p FROM Product p WHERE p.enabled = True ";
	
	@Override
	public List<Product> search(ProductSearchDTO criteria, int first, int size) {
		StringBuilder jpqlQuery = new StringBuilder(SEARCH_QUERY);
		Map<String, Object> queryProperties = new HashMap<>();
		if(criteria.getName() != null && ! criteria.getName().isBlank()) {
			jpqlQuery.append("AND p.name LIKE :name ");
			queryProperties.put("name", "%" + criteria.getName() + "%");
		}
		if(criteria.getDescription() != null && ! criteria.getDescription().isBlank()) {
			jpqlQuery.append("AND p.description LIKE :description ");
			queryProperties.put("description", "%" + criteria.getDescription() + "%");			
		}
		if(criteria.getBeginPrice() != null && ! BigDecimal.ZERO.equals(criteria.getBeginPrice())) {
			jpqlQuery.append("AND p.price >= :beginPrice ");
			queryProperties.put("beginPrice", criteria.getBeginPrice());
		}
		if(criteria.getEndPrice() != null && ! BigDecimal.ZERO.equals(criteria.getEndPrice())) {
			jpqlQuery.append("AND p.price <= :endPrice ");
			queryProperties.put("endPrice", criteria.getEndPrice());
		}
		if(criteria.getCategory() != null) {
			jpqlQuery.append("AND p.category = :category ");
			queryProperties.put("category", criteria.getCategory());			
		}
		
		TypedQuery<Product> query = em.createQuery(jpqlQuery.toString(), Product.class)
				.setFirstResult(first)
				.setMaxResults(size);
		
		queryProperties.entrySet().stream().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));				
		
		return query.getResultList();
	}

	private static final String COUNT_QUERY = "SELECT count(p.id) FROM Product p WHERE p.enabled = True ";
	
	@Override
	public Long count(ProductSearchDTO criteria) {
		StringBuilder jpqlQuery = new StringBuilder(COUNT_QUERY);
		Map<String, Object> queryProperties = new HashMap<>();
		if(criteria.getName() != null && ! criteria.getName().isBlank()) {
			jpqlQuery.append("AND p.name LIKE :name ");
			queryProperties.put("name", "%" + criteria.getName() + "%");
		}
		if(criteria.getDescription() != null && ! criteria.getDescription().isBlank()) {
			jpqlQuery.append("AND p.description LIKE :description ");
			queryProperties.put("description", "%" + criteria.getDescription() + "%");			
		}
		if(criteria.getBeginPrice() != null && ! BigDecimal.ZERO.equals(criteria.getBeginPrice())) {
			jpqlQuery.append("AND p.price >= :beginPrice ");
			queryProperties.put("beginPrice", criteria.getBeginPrice());
		}
		if(criteria.getEndPrice() != null && ! BigDecimal.ZERO.equals(criteria.getEndPrice())) {
			jpqlQuery.append("AND p.price <= :endPrice ");
			queryProperties.put("endPrice", criteria.getEndPrice());
		}
		if(criteria.getCategory() != null) {
			jpqlQuery.append("AND p.category = :category ");
			queryProperties.put("category", criteria.getCategory());			
		}
		
		Query query = em.createQuery(jpqlQuery.toString());
		
		queryProperties.entrySet().stream().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));				
		
		return (Long) query.getSingleResult();
	}

	@Override
	public List<Product> findByIds(List<Long> ids) {
		if(ids == null || ids.isEmpty()) return new ArrayList<>();
		return em.createNamedQuery("Product.findByIds", Product.class).setParameter("ids", ids).getResultList();
	}
	
}
