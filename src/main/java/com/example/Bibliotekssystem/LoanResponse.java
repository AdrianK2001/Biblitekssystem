package com.example.Bibliotekssystem;

import com.example.Bibliotekssystem.model.Loan;


public class LoanResponse {



        private Loan loan;

        private User user;

        public LoanResponse(Loan loan, User user) {
            this.loan = loan;
            this.user = user;
        }

        public LoanResponse() {
        }

        public Loan getLoan() {
            return loan;
        }

        public void setLoan(Loan order) {
            this.loan = order;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

