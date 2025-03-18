package com.example.Bibliotekssystem.BookRepository;

import com.example.Bibliotekssystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
