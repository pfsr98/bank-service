package pe.edu.unmsm.bank.infrastructure.validation;

import pe.edu.unmsm.bank.infrastructure.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClass();

    String message() default Constants.INVALID_ENUM;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}