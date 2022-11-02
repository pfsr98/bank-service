package pe.edu.unmsm.bank.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
}
