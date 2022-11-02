package pe.edu.unmsm.bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import pe.edu.unmsm.bank.customer.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankApplicationTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGetCustomers() {
        ResponseEntity<List<Customer>> response = testRestTemplate
                .exchange("http://localhost:" + port + "/api/customers", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

}
