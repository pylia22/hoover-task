package com.andersen.hoover.oleg.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = HooverPositionValidator.class)
public @interface PositionValidation {
    String message() default "Invalid hoover position";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
