package pe.edu.unmsm.bank.account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();
    List<Account> getAccountsByCustomerId(Long customerId);
    Account getAccountById(Long id);
    Account createAccount(Account account);
    Account updateAccountBalance(Long id, AccountBalance accountBalance);
    Account updateAccount(Long id, Account account);
    void deleteAccountById(Long id);

}
