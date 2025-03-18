package com.example.Bibliotekssystem;
import com.example.Bibliotekssystem.model.Loan;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class LoanService {

    private final com.example.Bibliotekssystem.LoanRepository loanRepository;
    private final WebClient webClient;

    public LoanService(com.example.Bibliotekssystem.LoanRepository loanRepository, WebClient.Builder webClientBuilder) {
        this.loanRepository = loanRepository;
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    public Loan createLoan(Long bookId, String borrower){
        webClient.put()
                .uri("/{id}/availability?available=false", bookId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        Loan loan = new Loan(bookId, borrower);
        return loanRepository.save(loan);
    }
    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }
}
