/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Tekija;
import wad.domain.Uutinen;
import wad.domain.Uutiskuva;
import wad.repository.TekijaRepository;
import wad.repository.UutinenRepository;
import wad.repository.UutiskuvaRepository;

/**
 *
 * @author kari
 */
@Controller
public class UutinenController {

    @Autowired
    private UutinenRepository uutinenRepo;
    @Autowired
    private UutiskuvaRepository uutiskuvaRepo;
    @Autowired
    private TekijaRepository tekijaRepo;

    @PostMapping("/lisaa-uutinen")
    @Transactional
    public String lisaaUutinen(@RequestParam("kuva") MultipartFile kuva, @RequestParam("tekija") String tekijat,
            @RequestParam("otsikko") String otsikko, @RequestParam("ingressi") String ingressi, @RequestParam("teksti") String teksti) throws IOException {
        Long kuvaId = 0L;
        if (kuva.getContentType().equalsIgnoreCase("image/jpeg")) {
            Uutiskuva uutiskuva = new Uutiskuva();
            uutiskuva.setSisalto(kuva.getBytes());
            uutiskuvaRepo.save(uutiskuva);
            uutiskuvaRepo.flush();
            kuvaId = uutiskuva.getId();
        }
        Uutinen uutinen = new Uutinen();
        uutinen.setIngressi(ingressi);
        uutinen.setKuvaId(kuvaId);
        uutinen.setOtsikko(otsikko);
        uutinen.setTeksti(teksti);
        String[] tekijatArray = tekijat.split(",");
        for (String nimi : tekijatArray) {
            Tekija tekija = new Tekija();
            tekija.setNimi(nimi.trim());
            if (tekijaRepo.findByNimi(nimi) == null) {
                tekijaRepo.save(tekija);
            }
            if (uutinen.getTekijat() == null) {
                uutinen.setTekijat(new ArrayList<>());
            }
            uutinen.getTekijat().add(tekija);
        }
        uutinen.setJulkaisuaika(LocalDate.now());
        uutinenRepo.save(uutinen);
        return "redirect:/Ge6TQjGctR";
    }

    @GetMapping("/uutinen/{id}")
    @Transactional
    public String haeYksiUutinen(Model model, @PathVariable Long id) {
        Uutinen uutinen = uutinenRepo.getOne(id);
        uutinen.kasvata();
        uutinenRepo.save(uutinen);
        uutinenRepo.flush();
        model.addAttribute("uutinen", uutinen);
        Pageable luetuimmat = PageRequest.of(0, 10, Sort.Direction.DESC, "lukukerrat");
        Pageable kymmenenUusinta = PageRequest.of(0, 5, Sort.Direction.ASC, "julkaisuaika");
        model.addAttribute("uusimmat", uutinenRepo.findAll(kymmenenUusinta));
        model.addAttribute("luetuimmat", uutinenRepo.findAll(luetuimmat));
        return "uutinen";
    }

    @GetMapping("/kuvapankki/{id}")
    @Transactional
    @ResponseBody
    public byte[] naytaKuva(@PathVariable Long id) {
        return uutiskuvaRepo.findById(id).get().getSisalto();
    }
    
    @DeleteMapping("/uutinen/{id}")
    public String poistaUutinen(@PathVariable Long id) {
        uutinenRepo.deleteById(id);
        return "redirect:/";
    }

}
