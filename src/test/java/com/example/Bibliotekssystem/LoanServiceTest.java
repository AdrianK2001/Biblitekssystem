package com.example.Bibliotekssystem;

import com.example.Bibliotekssystem.model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;  // Mock av LoanRepository för att isolera tjänstelogiken från databasen

    @Mock
    private WebClient.Builder webClientBuilder;  // Mock av WebClient.Builder för att isolera externa beroenden

    @InjectMocks
    private LoanService loanService;  // LoanService som testas

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initiera mock-objekt
        WebClient webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);  // Mocka WebClient för att undvika riktiga externa anrop
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);  // Mocka baseUrl-konfigurationen
        when(webClientBuilder.build()).thenReturn(webClient);  // Mocka WebClient byggmetoden
    }

    // Enhetstest för att verifiera att metoden createLoan() sparar ett lån och returnerar det korrekt
    @Test
    void shouldCreateLoan() {
        // Arrange
        Loan loan = new Loan(1L, "John Doe");
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // Act: Anropar createLoan för att skapa ett nytt lån
        Loan createdLoan = loanService.createLoan(1L, "John Doe");

        // Assert
        assertEquals("John Doe", createdLoan.getBorrower());  // Kontrollera att borrower är korrekt
        verify(loanRepository).save(any(Loan.class));  // Kontrollera att save-metoden har anropats på repository
    }

    // Enhetstest för att verifiera att getAllLoans() returnerar rätt lista av lån
    @Test
    void shouldGetAllLoans() {
        // Arrange
        Loan loan = new Loan(1L, "John Doe");
        when(loanRepository.findAll()).thenReturn(List.of(loan));

        // Act
        List<Loan> loans = loanService.getAllLoans();

        // Assert: Verifierar att listan innehåller ett lån och att borrower är korrekt
        assertEquals(1, loans.size());  // Kontrollera att listan innehåller exakt ett lån
        assertEquals("John Doe", loans.get(0).getBorrower());  // Kontrollera att borrower är korrekt på det första lånet
    }
}