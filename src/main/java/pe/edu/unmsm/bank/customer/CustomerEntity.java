package pe.edu.unmsm.bank.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@Table(name = "customers")
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends Person {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

}
