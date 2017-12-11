package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Uutinen;
import wad.repository.UutinenRepository;
import wad.repository.UutiskuvaRepository;

@Controller
public class DefaultController {

    @Autowired
    private UutinenRepository uutinenRepository;
    @Autowired
    private UutiskuvaRepository uutiskuvaRepo;

    @GetMapping("/")
    public String list(Model model) {
        Pageable viisiUusinta = PageRequest.of(0, 5, Sort.Direction.ASC, "julkaisuaika");
        Pageable luetuimmat = PageRequest.of(0, 10, Sort.Direction.DESC, "lukukerrat");
        Pageable kymmenenUusinta = PageRequest.of(0, 5, Sort.Direction.ASC, "julkaisuaika");
        model.addAttribute("uutiset", uutinenRepository.findAll(viisiUusinta));
        model.addAttribute("uusimmat", uutinenRepository.findAll(kymmenenUusinta));
        model.addAttribute("luetuimmat", uutinenRepository.findAll(luetuimmat));
        return "index";
    }
}
