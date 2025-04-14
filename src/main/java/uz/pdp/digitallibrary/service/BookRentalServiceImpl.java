package uz.pdp.digitallibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.digitallibrary.entity.Book;
import uz.pdp.digitallibrary.entity.BookRental;
import uz.pdp.digitallibrary.entity.User;
import uz.pdp.digitallibrary.exception.RestException;
import uz.pdp.digitallibrary.payload.BookDTO;
import uz.pdp.digitallibrary.repository.BookRentalRepository;
import uz.pdp.digitallibrary.repository.BookRepository;
import uz.pdp.digitallibrary.repository.UserRepository;
import uz.pdp.digitallibrary.util.ApiResult;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRentalServiceImpl implements BookRentalService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookRentalRepository bookRentalRepository;

    @Override
    public ApiResult<String> borrowBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(()-> new RestException("book not found"));

        if (!book.isAvailable())
            throw new RestException("Book is already borrowed");

        String currenUser = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(currenUser).orElseThrow(() ->
                RestException.notFound("user not found with username", currenUser)
        );

        BookRental rental = new BookRental();
        rental.setBook(book);
        rental.setUser(user);
        rental.setRentalDate(LocalDate.now());

        book.setAvailable(false);

        bookRentalRepository.save(rental);
        bookRepository.save(book);
        return ApiResult.success("Book Borrowed");
    }

    @Override
    public ApiResult<String> returnBook(Long id) {

        String currenUser = SecurityContextHolder.getContext().getAuthentication().getName();

        bookRepository.findById(id).orElseThrow(() -> new RestException("Book not found with"));

        BookRental rental = bookRentalRepository.findByBookIdAndUserUsernameAndReturnDateIsNull(id, currenUser)
                .orElseThrow(() -> new RestException("Book not borrowed by this user"));

        rental.setReturnDate(LocalDate.now());
        Book book = rental.getBook();
        book.setAvailable(true);

        bookRepository.save(book);
        bookRentalRepository.save(rental);

        return ApiResult.success("Book Successfully Returned");
    }

    @Override
    public ApiResult<List<BookDTO>> getMyBorrowedBooks() {

        String currenUser = SecurityContextHolder.getContext().getAuthentication().getName();

        List<BookRental> myRentalBooks = bookRentalRepository.findByUserUsernameAndReturnDateIsNull(currenUser);

        List<BookDTO> list = myRentalBooks.stream()
                .map(bookRental -> new BookDTO(
                        bookRental.getBook().getId(),
                        bookRental.getBook().getTitle(),
                        false
                )).toList();
        return ApiResult.success(list);

    }
}
