package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("uutiset", uutinenRepository.findAll());
        return "index";
    }
}
