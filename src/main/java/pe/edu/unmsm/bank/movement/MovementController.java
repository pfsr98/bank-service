package pe.edu.unmsm.bank.movement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movements")
public class MovementController {

    private final MovementService service;

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        return ResponseEntity.ok(service.getAllMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getMovementById(id));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<List<Movement>> getMovementByAccountId(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(service.getMovementsByAccountId(accountId));
    }

    @PostMapping
    public ResponseEntity<Movement> createMovement(@Valid @RequestBody Movement movement) {
        Movement createdMovement = service.createMovement(movement);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdMovement.getMovementId())
                .toUri()).body(createdMovement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movement> updateMovement(@PathVariable("id") Long id, @Valid @RequestBody Movement movement) {
        return ResponseEntity.ok(service.updateMovement(id, movement));
    }

    @PatchMapping("/{id}/value")
    public ResponseEntity<Movement> updateMovementValue(@PathVariable("id") Long id, @Valid @RequestBody MovementValue movementValue) {
        return ResponseEntity.ok(service.updateMovementValue(id, movementValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovementById(@PathVariable("id") Long id) {
        service.deleteMovementById(id);
        return ResponseEntity.noContent().build();
    }

}
