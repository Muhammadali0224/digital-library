package uz.pdp.digitallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.digitallibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}