package ir.encoding.order.validation.order;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckMinimumOrderPriceValidator.class)
public @interface CheckMinimumOrderPrice {

    String message() default "{order.price.can.not.be.less.than.50.box}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };	
	
}
