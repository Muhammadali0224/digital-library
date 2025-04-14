package uz.pdp.digitallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.digitallibrary.entity.BookRental;

import java.util.List;
import java.util.Optional;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
    Optional<BookRental> findByBookIdAndUserUsernameAndReturnDateIsNull(Long id, String currenUser);

    List<BookRental> findByUserUsernameAndReturnDateIsNull(String username);
}