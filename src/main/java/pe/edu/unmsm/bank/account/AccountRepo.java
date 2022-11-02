package pe.edu.unmsm.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unmsm.bank.customer.CustomerEntity;

import java.util.List;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByCustomer(CustomerEntity customer);
}
