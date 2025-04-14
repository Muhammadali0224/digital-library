package uz.pdp.digitallibrary.entity.template;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsLongEntity extends AbsAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
