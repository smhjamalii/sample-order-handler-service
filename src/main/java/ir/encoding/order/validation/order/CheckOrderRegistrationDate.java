package ir.encoding.order.validation.order;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckOrderRegistrationDateValidator.class)
public @interface CheckOrderRegistrationDate {

    String message() default "{order.registration.time.is.between.8am.and.7pm}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };	
	
}
