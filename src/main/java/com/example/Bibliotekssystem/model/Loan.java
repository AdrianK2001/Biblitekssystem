package com.example.Bibliotekssystem.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookid;
    private String borrower;

    public Loan(Long bookId, String borrower) {
    }

    public long getId(){
        return id;
    }

    public String getUserId() {
        return null;
    }
}
