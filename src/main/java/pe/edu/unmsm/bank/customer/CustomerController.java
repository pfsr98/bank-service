package pe.edu.unmsm.bank.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = service.createCustomer(customer);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCustomer.getCustomerId())
                .toUri()).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(service.updateCustomer(id, customer));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Customer> updateCustomerPassword(@PathVariable("id") Long id, @Valid @RequestBody CustomerPassword customerPassword) {
        return ResponseEntity.ok(service.updateCustomerPassword(id, customerPassword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        service.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
