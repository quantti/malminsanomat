package wad.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Uutinen extends AbstractPersistable<Long> {

    private String otsikko;
    private String ingressi;
    private String teksti;
    @OneToOne
    private Uutiskuva kuva;
    private LocalDate julkaisuaika;
    @ManyToMany
    private List<Tekija> tekijat;
}
