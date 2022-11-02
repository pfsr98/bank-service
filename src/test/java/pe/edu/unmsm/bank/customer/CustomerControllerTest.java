package pe.edu.unmsm.bank.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    private List<Customer> customers;

    private Customer customer;

    @BeforeEach
    void init() throws IOException {
        customers = objectMapper.readValue(new ClassPathResource("getAllCustomers.json").getInputStream(), new TypeReference<>() {
        });
        customer = objectMapper.readValue(new ClassPathResource("createCustomer.json").getInputStream(), Customer.class);
    }

    @Test
    void getAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createCustomer() throws Exception {
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.name", is("Marco Aurelio")))
                .andExpect(jsonPath("$.gender", is(Gender.MASCULINO.name())))
                .andExpect(jsonPath("$.age", is(21)))
                .andExpect(jsonPath("$.address", is("Av. Las Palmeras")))
                .andExpect(jsonPath("$.telephone", is("912877654")))
                .andExpect(jsonPath("$.password", is("12345")))
                .andExpect(jsonPath("$.status", is(true)));
    }

}