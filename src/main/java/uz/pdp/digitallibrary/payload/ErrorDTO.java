package uz.pdp.digitallibrary.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {


    private LocalDate timestamp;
    private String message;
    private int status;

}
