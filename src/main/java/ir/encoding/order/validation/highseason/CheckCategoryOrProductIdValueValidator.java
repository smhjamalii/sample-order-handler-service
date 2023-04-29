package ir.encoding.order.validation.highseason;

import ir.encoding.order.view.dto.product.HighSeasonDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckCategoryOrProductIdValueValidator implements ConstraintValidator<CheckCategoryOrProductIdValue, HighSeasonDTO> {

	@Override
	public boolean isValid(HighSeasonDTO value, ConstraintValidatorContext context) {
		return value.getCategory() != null || value.getProductId() != null;
	}

}
