package pe.edu.unmsm.bank.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.bank.customer.Customer;
import pe.edu.unmsm.bank.customer.CustomerEntity;
import pe.edu.unmsm.bank.customer.CustomerMapper;
import pe.edu.unmsm.bank.customer.CustomerService;
import pe.edu.unmsm.bank.infrastructure.exception.AccountNotFoundException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo repo;
    private final CustomerService customerService;

    @Override
    public List<Account> getAllAccounts() {
        return repo.findAll()
                .stream()
                .map(AccountMapper::toAccount)
                .toList();
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        CustomerEntity customer = CustomerMapper.toCustomerEntity(customerService.getCustomerById(customerId));
        return repo.findByCustomer(customer)
                .stream()
                .map(AccountMapper::toAccount)
                .toList();
    }

    @Override
    public Account getAccountById(Long id) {
        return repo.findById(id)
                .map(AccountMapper::toAccount)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public Account createAccount(Account newAccount) {
        Long customerId = newAccount.getCustomer().getCustomerId();
        Account account = newAccount.toBuilder().customer(customerService.getCustomerById(customerId)).build();
        AccountEntity createdAccount = repo.save(AccountMapper.toAccountEntity(account));
        return AccountMapper.toAccount(createdAccount);
    }

    @Override
    public Account updateAccountBalance(Long id, AccountBalance accountBalance) {
        return repo.findById(id)
                .map(customer -> customer.toBuilder()
                        .initialBalance(accountBalance.getInitialBalance())
                        .build())
                .map(repo::save)
                .map(AccountMapper::toAccount)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public Account updateAccount(Long id, Account changedAccount) {
        Customer customer = customerService.getCustomerById(changedAccount.getCustomer().getCustomerId());
        return repo.findById(id)
                .map(account -> account.toBuilder()
                        .accountNumber(changedAccount.getAccountNumber())
                        .accountType(AccountType.valueOf(changedAccount.getAccountType()))
                        .initialBalance(changedAccount.getInitialBalance())
                        .status(changedAccount.getStatus())
                        .customer(CustomerMapper.toCustomerEntity(customer))
                        .build())
                .map(repo::save)
                .map(AccountMapper::toAccount)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public void deleteAccountById(Long id) {
        repo.deleteById(id);
    }
}
