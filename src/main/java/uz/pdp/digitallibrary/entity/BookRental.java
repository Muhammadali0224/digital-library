package uz.pdp.digitallibrary.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.digitallibrary.entity.template.AbsLongEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BookRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate rentalDate;

    private LocalDate returnDate;

}

