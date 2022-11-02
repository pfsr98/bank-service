package pe.edu.unmsm.bank.account;

import lombok.*;
import pe.edu.unmsm.bank.customer.CustomerEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Builder(toBuilder = true)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private Double initialBalance;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "persons_fk"))
    private CustomerEntity customer;

}
