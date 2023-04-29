package ir.encoding.order.validation.order;

import java.time.LocalTime;

import ir.encoding.order.view.dto.order.OrderDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckOrderRegistrationDateValidator implements ConstraintValidator<CheckOrderRegistrationDate, OrderDTO> {

	private static final LocalTime REGISTRATION_BEGIN_DATE = LocalTime.of(1, 59, 59);
	private static final LocalTime REGISTRATION_END_DATE = LocalTime.of(19, 0, 0);
	
	@Override
	public boolean isValid(OrderDTO order, ConstraintValidatorContext context) {		
		return order.getRegistrationDateTme().toLocalTime().isAfter(REGISTRATION_BEGIN_DATE) &&
				order.getRegistrationDateTme().toLocalTime().isBefore(REGISTRATION_END_DATE);
	}

}
