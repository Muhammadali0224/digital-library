package uz.pdp.digitallibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.digitallibrary.entity.template.AbsLongEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Book extends AbsLongEntity {

    @NotBlank
    private String title;

    private boolean isAvailable;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<BookRental> bookRentals;



}
