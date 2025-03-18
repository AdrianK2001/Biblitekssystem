package com.example.Bibliotekssystem.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookid;
    private String borrower;

    public Loan(Long bookId, String borrower) {
    }


    public String getUserId() {
    }
}
