package uz.pdp.digitallibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.digitallibrary.payload.BookDTO;
import uz.pdp.digitallibrary.service.BookRentalService;
import uz.pdp.digitallibrary.service.BookService;
import uz.pdp.digitallibrary.util.ApiResult;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRentalService bookRentalService;

    @GetMapping
    public ResponseEntity<ApiResult<List<BookDTO>>> getBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());

    }

    @GetMapping("my-borrowed")
    public ResponseEntity<ApiResult<List<BookDTO>>> getMyBorrowedBooks() {
        return ResponseEntity.ok(bookRentalService.getMyBorrowedBooks());

    }

    @PreAuthorize("hasRole(T(uz.pdp.digitallibrary.enums.Role).ADMIN.name())")
    @PostMapping
    public ResponseEntity<ApiResult<String>> addBook(@Valid @RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(bookService.addBook(bookDTO));

    }

    @PutMapping("/{id}/borrow")
    public ResponseEntity<ApiResult<String>> borrowBook(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(bookRentalService.borrowBook(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<ApiResult<String>> returnBook(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(bookRentalService.returnBook(id));
    }





}
