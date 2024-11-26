package com.example.ecommerce.component;

import com.example.ecommerce.constraint.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {

    String message() default "Password must be at least 8 characters long, include one uppercase letter, one number, and one special character.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
