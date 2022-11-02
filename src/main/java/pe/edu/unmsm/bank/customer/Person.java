package pe.edu.unmsm.bank.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String telephone;

}
