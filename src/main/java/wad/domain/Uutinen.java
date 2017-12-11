package wad.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
    @Lob
    @Column(columnDefinition = "text")
    private String teksti;
    Long kuvaId;
    private LocalDate julkaisuaika;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tekija> tekijat;
    @ManyToMany(mappedBy = "uutiset")
    private List<Kategoria> kategoriat;
    private int lukukerrat;
    
    public void kasvata() {
        this.lukukerrat++;
    }
}
