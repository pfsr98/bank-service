package pe.edu.unmsm.bank.infrastructure.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValidator, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidator annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return acceptedValues.contains(value.toString());
    }



}
