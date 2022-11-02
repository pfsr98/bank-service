package pe.edu.unmsm.bank.account;

import pe.edu.unmsm.bank.customer.CustomerMapper;
import pe.edu.unmsm.bank.infrastructure.Constants;

public class AccountMapper {

    private AccountMapper() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static Account toAccount(AccountEntity accountEntity) {
        return Account.builder()
                .accountId(accountEntity.getAccountId())
                .accountNumber(accountEntity.getAccountNumber())
                .accountType(accountEntity.getAccountType().name())
                .initialBalance(accountEntity.getInitialBalance())
                .status(accountEntity.getStatus())
                .customer(CustomerMapper.toCustomer(accountEntity.getCustomer()))
                .build();
    }

    public static AccountEntity toAccountEntity(Account account) {
        return AccountEntity.builder()
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .accountType(AccountType.valueOf(account.getAccountType()))
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .customer(CustomerMapper.toCustomerEntity(account.getCustomer()))
                .build();
    }

}
