package pe.edu.unmsm.bank.movement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unmsm.bank.account.*;
import pe.edu.unmsm.bank.infrastructure.Constants;
import pe.edu.unmsm.bank.infrastructure.PropertyConfig;
import pe.edu.unmsm.bank.infrastructure.exception.DomainException;
import pe.edu.unmsm.bank.infrastructure.exception.MovementNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {
    private final MovementRepo repo;
    private final AccountService accountService;
    private final PropertyConfig propertyConfig;

    @Override
    public List<Movement> getAllMovements() {
        return repo.findAll()
                .stream()
                .map(MovementMapper::toMovement)
                .toList();
    }

    @Override
    public Movement getMovementById(Long id) {
        return repo.findById(id)
                .map(MovementMapper::toMovement)
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Override
    public List<Movement> getMovementsByAccountId(Long accountId) {
        AccountEntity existingAccount = AccountMapper.toAccountEntity(accountService.getAccountById(accountId));
        return repo.findByAccount(existingAccount).stream().map(MovementMapper::toMovement).toList();
    }

    @Override
    public List<Movement> getMovementsByAccountIdAndDateRange(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        AccountEntity existingAccount = AccountMapper.toAccountEntity(accountService.getAccountById(accountId));
        return repo.findByAccountAndDateBetween(existingAccount, startDate, endDate).stream().map(MovementMapper::toMovement).toList();
    }

    @Override
    @Transactional
    public Movement createMovement(Movement newMovement) {
        log.info("Registrando movimiento {}", newMovement);
        Account account = accountService.getAccountById(newMovement.getAccount().getAccountId());
        double newBalance;

        if (newMovement.getMovementType().equals(MovementType.RETIRO.name())) {
            log.info("Validando débito...");
            newBalance = account.getInitialBalance() - newMovement.getValue();
            if (newBalance < 0) throw new DomainException(Constants.BALANCE_UNAVAILABLE);
            double debits = getMovementsByAccountId(account.getAccountId()).stream()
                    .filter(movement -> movement.getMovementType().equals(MovementType.RETIRO.name()))
                    .mapToDouble(Movement::getValue)
                    .sum();
            if (debits + newMovement.getValue() > propertyConfig.getDiaryLimit())
                throw new DomainException(Constants.DAILY_QUOTA_EXCEEDED);
            log.info("Débito validado correctamente");
        } else {
            newBalance = account.getInitialBalance() + newMovement.getValue();
        }

        AccountBalance accountBalance = AccountBalance.builder().initialBalance(newBalance).build();
        Account updatedAccount = accountService.updateAccountBalance(account.getAccountId(), accountBalance);
        log.info("Saldo de cuenta actualizada: {}", updatedAccount);
        Movement movement = newMovement.toBuilder().account(updatedAccount).balance(account.getInitialBalance()).build();
        MovementEntity createdMovement = repo.save(MovementMapper.toMovementEntity(movement));
        log.info("Movimiento registrado: {}", createdMovement);
        return MovementMapper.toMovement(createdMovement);
    }

    @Override
    public Movement updateMovementValue(Long id, MovementValue movementValue) {
        return repo.findById(id)
                .map(customer -> customer.toBuilder()
                        .value(movementValue.getValue())
                        .build())
                .map(repo::save)
                .map(MovementMapper::toMovement)
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Override
    public Movement updateMovement(Long id, Movement changedMovement) {
        Account account = accountService.getAccountById(changedMovement.getAccount().getAccountId());
        return repo.findById(id)
                .map(movement -> movement.toBuilder()
                        .date(changedMovement.getDate())
                        .movementType(MovementType.valueOf(changedMovement.getMovementType()))
                        .value(changedMovement.getValue())
                        .balance(changedMovement.getBalance())
                        .account(AccountMapper.toAccountEntity(account))
                        .build())
                .map(repo::save)
                .map(MovementMapper::toMovement)
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Override
    public void deleteMovementById(Long id) {
        repo.deleteById(id);
    }
}
