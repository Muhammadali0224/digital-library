package uz.pdp.digitallibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.digitallibrary.entity.Book;
import uz.pdp.digitallibrary.payload.BookDTO;
import uz.pdp.digitallibrary.mapper.BookMapper;
import uz.pdp.digitallibrary.repository.BookRepository;
import uz.pdp.digitallibrary.util.ApiResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public ApiResult<List<BookDTO>> getAllBooks() {

        if(bookRepository.count() == 0)
            return ApiResult.error("No books found");


        List<Book> books = bookRepository.findAll();

        return ApiResult.success(bookMapper.toDTOList(books));
    }

    @Override
    public ApiResult<String> addBook(BookDTO bookDTO) {

        Book book = bookMapper.toBook(bookDTO);

        book.setAvailable(true);

        bookRepository.save(book);

        return ApiResult.success("Book added successfully");

    }


}
