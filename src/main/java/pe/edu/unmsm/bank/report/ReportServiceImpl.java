package pe.edu.unmsm.bank.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.bank.account.AccountService;
import pe.edu.unmsm.bank.movement.MovementService;
import pe.edu.unmsm.bank.movement.MovementType;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final AccountService accountService;
    private final MovementService movementService;

    @Override
    public List<AccountStatement> getAccountStatement(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountService.getAccountsByCustomerId(customerId).stream()
                .flatMap(account -> movementService.getMovementsByAccountIdAndDateRange(account.getAccountId(), startDate, endDate).stream())
                .map(movement -> AccountStatement.builder()
                        .date(movement.getDate())
                        .customer(movement.getAccount().getCustomer().getName())
                        .accountType(movement.getAccount().getAccountType())
                        .initialBalance(movement.getBalance())
                        .status(movement.getAccount().getStatus())
                        .movement(movement.getMovementType().equals(MovementType.DEPOSITO.name())
                                ? movement.getValue() : movement.getValue() * -1)
                        .availableBalance(movement.getMovementType().equals(MovementType.DEPOSITO.name())
                                ? movement.getBalance() + movement.getValue() : movement.getBalance() - movement.getValue())
                        .build())
                .toList();
    }

}
