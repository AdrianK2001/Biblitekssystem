// LoanControllerTest.java
package com.example.Bibliotekssystem;

import com.example.Bibliotekssystem.model.Loan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc används för att simulera HTTP-anrop till controller

    @MockBean
    private LoanRepository loanRepository;  // Mockar LoanRepository för att isolera testet från den verkliga databasen

    @MockBean
    private WebClient.Builder webClientBuilder;  // Mockar WebClient.Builder för att isolera externa API-anrop

    // Testar att skapa ett lån via LoanController och verifierar att HTTP-anropet hanteras korrekt
    @Test
    void shouldCreateLoan() throws Exception {
        // Arrange: Förbereder testdata genom att skapa ett lån och mocka repositoryn för att returnera detta lån
        Loan loan = new Loan(1L, "John Doe");
        Mockito.when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // Act & Assert: Simulerar ett POST-anrop till /loan och verifierar att lånet sparas och att HTTP-statusen är 200 OK
        mockMvc.perform(post("/loan")
                        .contentType("application/json")  // Anger att vi skickar JSON-data
                        .content(new ObjectMapper().writeValueAsString(loan)))  // Omvandlar loan-objektet till JSON
                .andExpect(status().isOk())  // Verifierar att HTTP-statusen är 200 OK
                .andExpect(jsonPath("$.borrower").value("John Doe"));  // Verifierar att 'borrower' fältet är korrekt i svaret
    }
}

