package com.hackaton.jeffrey.controller;

import com.hackaton.jeffrey.entity.Adn;
import com.hackaton.jeffrey.entity.Estadistica;
import com.hackaton.jeffrey.repository.IEstadisticaDao;
import com.hackaton.jeffrey.service.AdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class MutanteControllers {
    @Autowired
    private AdnService adnService;
    @Autowired
    private IEstadisticaDao estadisticaDao;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody Adn dna){
        boolean isMutant = adnService.isMutant(dna.getDna());
        adnService.guardarEstadistica();
        if (isMutant){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public List<Estadistica> getStats(){
        return adnService.findAll();
    }

}
