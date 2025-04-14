package uz.pdp.digitallibrary.service;


import uz.pdp.digitallibrary.payload.BookDTO;
import uz.pdp.digitallibrary.util.ApiResult;

import java.util.List;

public interface BookService {

    ApiResult<List<BookDTO>> getAllBooks();

    ApiResult<String> addBook(BookDTO bookDTO);

}
