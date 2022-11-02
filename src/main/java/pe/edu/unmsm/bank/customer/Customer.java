package pe.edu.unmsm.bank.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import pe.edu.unmsm.bank.infrastructure.validation.EnumValidator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@ToString
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    private final Long customerId;
    private final String name;

    @NotNull
    @EnumValidator(enumClass = Gender.class)
    private final String gender;

    private final Integer age;

    @Size(min = 8, max = 8)
    private final String dni;

    private final String address;

    @Size(min = 9, max = 9)
    private final String telephone;

    private final String password;
    private final Boolean status;

}
