package pe.edu.unmsm.bank.movement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pe.edu.unmsm.bank.account.AccountEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movements")
@Builder(toBuilder = true)
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementId;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private MovementType movementType;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "accounts_fk"))
    private AccountEntity account;

}
