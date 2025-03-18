package com.example.Bibliotekssystem;
import com.example.Bibliotekssystem.model.Loan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/loan")

public class LoanController {

    private final WebClient webclient;

    private final LoanRepository loanRepository;


    public LoanController(WebClient.Builder webclientBuilder, LoanRepository loanRepository) {
        this.webclient = webclientBuilder.baseUrl("http://localhost:8081").build();
        this.loanRepository = loanRepository;
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan){
        return loanRepository.save(loan);

    }


    @GetMapping("/{id}")
    public Mono<LoanResponse> getLoanById(@PathVariable Long id){

        return loanRepository.findById(id).map( loan ->
                        webclient.get()
                                .uri("/users/" + loan.getUserId())
                                .retrieve().bodyToMono(User.class)
                                .map(user -> new LoanResponse(loan, user)))
                .orElse(Mono.empty());
    }



}
