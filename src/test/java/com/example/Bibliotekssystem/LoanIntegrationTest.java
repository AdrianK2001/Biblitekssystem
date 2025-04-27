// LoanIntegrationTest.java
package com.example.Bibliotekssystem;

import com.example.Bibliotekssystem.model.Loan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanIntegrationTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc används för att simulera HTTP-anrop till controller och verifiera svaren

    @Autowired
    private LoanRepository loanRepository;  // LoanRepository används för att interagera med databasen och hämta sparade objekt

    // SetUp-metod som körs innan varje test för att rensa databasen
    @BeforeEach
    void setUp() {
        loanRepository.deleteAll();  // Tömmer databasen för att säkerställa att testerna inte påverkas av tidigare data
    }

    // Integrationstest för att skapa ett lån via controller och sedan hämta det från databasen för att säkerställa att det är sparat korrekt
    @Test
    void shouldCreateAndGetLoan() throws Exception {
        Loan loan = new Loan(1L, "Jane Doe");

        // Act: Skapa ett lån via ett POST-anrop
        mockMvc.perform(post("/loan")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(loan)))  // Omvandlar Loan-objektet till JSON
                .andExpect(status().isOk())  // Verifierar att HTTP-statusen är 200 OK
                .andExpect(jsonPath("$.borrower").value("Jane Doe"));  // Verifierar att 'borrower' fältet är korrekt i svaret

        // Act: Hämta det skapade lånet från databasen och verifiera att det sparades korrekt
        Loan savedLoan = loanRepository.findAll().get(0);  // Hämtar det första lånet från databasen
        mockMvc.perform(get("/loan/" + savedLoan.getId()))  // Gör ett GET-anrop för att hämta lånet via controller
                .andExpect(status().isOk());  // Verifierar att HTTP-statusen är 200 OK
    }
}

