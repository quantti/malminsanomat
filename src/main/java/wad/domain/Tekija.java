/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author kari
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Tekija extends AbstractPersistable<Long>{
    private String nimi;
    @ManyToMany(mappedBy = "tekijat")
    private List<Uutinen> uutiset;
    private String kayttajanimi;
    private String salasana;
}
