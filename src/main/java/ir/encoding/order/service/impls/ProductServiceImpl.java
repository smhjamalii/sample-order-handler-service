package ir.encoding.order.service.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.encoding.order.data.jpa.entities.product.Product;
import ir.encoding.order.data.jpa.repositories.product.ProductRepository;
import ir.encoding.order.mappers.ProductMapper;
import ir.encoding.order.service.interfaces.ProductService;
import ir.encoding.order.view.dto.PagedResult;
import ir.encoding.order.view.dto.product.ProductDTO;
import ir.encoding.order.view.dto.product.ProductSearchDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public PagedResult<ProductDTO> search(ProductSearchDTO criteria, int start, int size) {
		List<Product> products = productRepo.search(criteria, start, size);
		Long totalRecords = productRepo.count(criteria);
		List<ProductDTO> result = products.stream().map(ProductMapper::getDTO).toList();
		
		PagedResult<ProductDTO> pagedResult = new PagedResult<>(result, totalRecords);
		return pagedResult;
	}

	@Override
	public List<ProductDTO> findByIds(List<Long> ids) {
		List<Product> products = productRepo.findByIds(ids);
		return products.stream().map(ProductMapper::getDTO).toList();
	}

}
