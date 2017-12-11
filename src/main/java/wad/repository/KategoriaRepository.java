/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Kategoria;

/**
 *
 * @author kari
 */
public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {

    public Kategoria findByNimi(String k);

    
}
