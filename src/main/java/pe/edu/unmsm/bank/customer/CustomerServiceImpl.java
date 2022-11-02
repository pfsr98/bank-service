package pe.edu.unmsm.bank.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.bank.infrastructure.exception.CustomerNotFoundException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo repo;

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll()
                .stream()
                .map(CustomerMapper::toCustomer)
                .toList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repo.findById(id)
                .map(CustomerMapper::toCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity createdCustomer = repo.save(CustomerMapper.toCustomerEntity(customer));
        return CustomerMapper.toCustomer(createdCustomer);
    }

    @Override
    public Customer updateCustomerPassword(Long id, CustomerPassword changedCustomer) {
        return repo.findById(id)
                .map(customer -> customer.toBuilder().password(changedCustomer.getPassword()).build())
                .map(repo::save)
                .map(CustomerMapper::toCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer updateCustomer(Long id, Customer changedCustomer) {
        return repo.findById(id)
                .map(customer -> customer.toBuilder()
                        .name(customer.getName())
                        .gender(Gender.valueOf(changedCustomer.getGender()))
                        .age(changedCustomer.getAge())
                        .dni(changedCustomer.getDni())
                        .address(changedCustomer.getAddress())
                        .telephone(changedCustomer.getTelephone())
                        .password(changedCustomer.getPassword())
                        .status(changedCustomer.getStatus())
                        .build())
                .map(repo::save)
                .map(CustomerMapper::toCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void deleteCustomerById(Long id) {
        repo.deleteById(id);
    }

}
