package uz.pdp.digitallibrary.service;

import uz.pdp.digitallibrary.payload.BookDTO;
import uz.pdp.digitallibrary.util.ApiResult;

import java.util.List;

public interface BookRentalService {

    ApiResult<String> borrowBook(Long id);

    ApiResult<String> returnBook(Long id);

    ApiResult<List<BookDTO>> getMyBorrowedBooks();
}
