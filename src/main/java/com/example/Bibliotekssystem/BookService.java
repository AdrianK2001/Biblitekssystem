package com.example.Bibliotekssystem;

import com.example.Bibliotekssystem.BookRepository.BookRepository;
import com.example.Bibliotekssystem.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void updateBookAvailability(Long id, boolean status) {
        bookRepository.findById(id)
                .map(b -> {
                    b.setAvailable(status);
                    return bookRepository.save(b);
                });

    }
}
