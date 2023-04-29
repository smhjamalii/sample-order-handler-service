package ir.encoding.order.service.interfaces;

import java.util.List;

import ir.encoding.order.view.dto.PagedResult;
import ir.encoding.order.view.dto.product.ProductDTO;
import ir.encoding.order.view.dto.product.ProductSearchDTO;

public interface ProductService {

	PagedResult<ProductDTO> search(ProductSearchDTO criteria, int start, int size);
	List<ProductDTO> findByIds(List<Long> ids);
}
