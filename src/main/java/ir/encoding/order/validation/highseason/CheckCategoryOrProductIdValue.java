package ir.encoding.order.validation.highseason;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCategoryOrProductIdValueValidator.class)
public @interface CheckCategoryOrProductIdValue {

    String message() default "{highseason.set.category.or.productId}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };	
	
}
