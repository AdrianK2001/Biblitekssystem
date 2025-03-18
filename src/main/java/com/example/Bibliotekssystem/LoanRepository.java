package com.example.Bibliotekssystem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Bibliotekssystem.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {}
