/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.repository.UutiskuvaRepository;

/**
 *
 * @author kari
 */
@Controller
public class KuvaController {
    
    @Autowired
    private UutiskuvaRepository uutiskuvaRepo;
    
    @GetMapping("/kuvapankki/{id}")
    @Transactional
    @ResponseBody
    public byte[] naytaKuva(@PathVariable Long id) {
        return uutiskuvaRepo.findById(id).get().getSisalto();
    }
}
