package com.example.Bibliotekssystem.controller;

import com.example.Bibliotekssystem.BookService;
import com.example.Bibliotekssystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getAllBooks() {
    return bookService.getAllBooks();
}

@GetMapping("/{id}")
public ResponseEntity<Book>getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
}

        @PostMapping
        public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
}



@PutMapping
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updateBook) {

        Book book = bookService.getBookById(id).orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setAvailable(updateBook.isAvailable());

        return ResponseEntity.ok (bookService.addBook(book));


}


}
