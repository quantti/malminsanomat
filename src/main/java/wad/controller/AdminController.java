/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wad.domain.Tekija;
import wad.repository.TekijaRepository;

/**
 *
 * @author kari
 */
@Controller
public class AdminController {
    @Autowired
    private TekijaRepository tekijaRepository;
    
    @PostConstruct
    public void init() {
//        tekijaRepository.save(new Tekija("Matti Virtanen", new ArrayList<>()));
//        tekijaRepository.save(new Tekija("Ville Hämäläinen", new ArrayList<>()));
//        tekijaRepository.save(new Tekija("Sari Matikainen", new ArrayList<>()));
    }

    @GetMapping("/Ge6TQjGctR")
    public String list(Model model) {
        model.addAttribute("tekijat", tekijaRepository.findAll());
        return "lisaa_uutinen";
    }
}
