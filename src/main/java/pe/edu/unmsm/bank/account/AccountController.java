package pe.edu.unmsm.bank.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(service.getAccountsByCustomerId(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        Account createdAccount = service.createAccount(account);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAccount.getAccountId())
                .toUri()).body(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @Valid @RequestBody Account account) {
        return ResponseEntity.ok(service.updateAccount(id, account));
    }

    @PatchMapping("/{id}/balance")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable("id") Long id, @Valid @RequestBody AccountBalance accountBalance) {
        return ResponseEntity.ok(service.updateAccountBalance(id, accountBalance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }

}
