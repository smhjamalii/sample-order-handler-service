package ir.encoding.order.facades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import ir.encoding.order.service.interfaces.HighSeasonService;
import ir.encoding.order.service.interfaces.ProductService;
import ir.encoding.order.view.dto.PagedResult;
import ir.encoding.order.view.dto.product.HighSeasonDTO;
import ir.encoding.order.view.dto.product.HighSeasonSearchDTO;
import ir.encoding.order.view.dto.product.ProductDTO;
import ir.encoding.order.view.dto.product.ProductSearchDTO;
import ir.encoding.order.view.dto.product.strategy.IncreaseProductPriceStrategy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductFacade implements Serializable {
	
	private final ProductService productService;
	private final HighSeasonService highSeasonService; 
	
	public PagedResult<ProductDTO> search(ProductSearchDTO criteria, int first, int size){
		
		PagedResult<ProductDTO> searchResult = productService.search(criteria, first, size);
		if(searchResult.getTotalCount() > 0) {
			
			List<Long> productIds = searchResult.getResult().stream().map(ProductDTO::getId).toList();			
			
			HighSeasonSearchDTO highSeasonSearchDTO = new HighSeasonSearchDTO(
					LocalDateTime.now(), LocalDateTime.now(),
					Collections.singletonList(criteria.getCategory()), productIds);
			
			List<HighSeasonDTO> highSeasons = highSeasonService.search(highSeasonSearchDTO);
			
			highSeasons.stream().forEach(hs -> {
				searchResult.getResult().stream()
					.filter(prd -> prd.getId().equals(hs.getProductId()) || prd.getCategory().equals(hs.getCategory()))
					.toList()
					.stream()
					.forEach(prd -> {
						if(prd.getProductPriceStrategy() instanceof IncreaseProductPriceStrategy) {
							IncreaseProductPriceStrategy ipps = (IncreaseProductPriceStrategy) prd.getProductPriceStrategy();
							ipps.addValue(hs.getIncreaseAmount());
						} else {
							prd.setProductPriceStrategy(new IncreaseProductPriceStrategy(hs.getIncreaseAmount()));
						}
					});
			});
			
		}
		return searchResult;
	}
	
}
