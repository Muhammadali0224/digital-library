package uz.pdp.digitallibrary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.digitallibrary.entity.Book;
import uz.pdp.digitallibrary.payload.BookDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {


    BookDTO toDTO (Book book);
    List<BookDTO> toDTOList (List<Book> books);



    @Mapping(target = "id", ignore = true)
    Book toBook (BookDTO bookDTO);
    List<Book> toBooks (List<BookDTO> bookDTOS);



}
