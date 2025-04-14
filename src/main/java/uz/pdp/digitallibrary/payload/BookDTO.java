package uz.pdp.digitallibrary.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.digitallibrary.entity.Book;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {

    private Long id;



    @NotBlank(message = "Title is required")
    private String title;
    private boolean isAvailable;
}