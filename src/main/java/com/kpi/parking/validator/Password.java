package com.kpi.parking.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordConstraintValidator.class})
public @interface Password {

    String message() default "Password must be valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
