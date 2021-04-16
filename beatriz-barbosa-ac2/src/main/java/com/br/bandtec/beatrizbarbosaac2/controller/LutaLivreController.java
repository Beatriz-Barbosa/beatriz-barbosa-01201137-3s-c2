package com.br.bandtec.beatrizbarbosaac2.controller;

import com.br.bandtec.beatrizbarbosaac2.dominio.Golpe;
import com.br.bandtec.beatrizbarbosaac2.dominio.Lutador;
import com.br.bandtec.beatrizbarbosaac2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lutadores")
public class LutaLivreController {

    @Autowired
    LutadorRepository lutadorRepository;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid Lutador novoLutador){
        lutadorRepository.save(novoLutador);
        return ResponseEntity.ok().build();
    }

    @GetMapping//NAO ESQUECE DE ORDENA
    public ResponseEntity getLutadores(){
        List<Lutador> lutadores = lutadorRepository.orderByForcaGolpe();
        return !lutadores.isEmpty() ? ResponseEntity.status(200).body(lutadores) :
                ResponseEntity.status(204).build();
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getContagemVivos(){
        return ResponseEntity.ok(lutadorRepository.countByIsVivo());
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity cadastrarUsuario(@PathVariable Integer id){
        if(lutadorRepository.existsById(id)) {
            Lutador lutador = lutadorRepository.getLutadorById(id);
            lutador.concentrar();
            lutadorRepository.flush();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

//E
    @PostMapping("/golpear" )
    public ResponseEntity darGolpe(@RequestBody Golpe ataque) {

        if (lutadorRepository.existsById(ataque.getIdLutadorBate()) &&
                lutadorRepository.existsById(ataque.getIdLutadorApanha())) {

            Lutador lutadorApanha = lutadorRepository.getLutadorById(ataque.getIdLutadorApanha());
            Lutador lutadorBate = lutadorRepository.getLutadorById(ataque.getIdLutadorBate());
                if(lutadorApanha.getIsvivo() && lutadorBate.getIsvivo()){
                    ArrayList<Lutador> lutadores = new ArrayList<>();

                    Double forca = lutadorBate.getForcaGolpe();

                    lutadorApanha.setVida(lutadorApanha.getVida() - lutadorBate.getForcaGolpe());
                    lutadores.add(lutadorBate);
                    lutadores.add(lutadorApanha);
                    lutadorRepository.flush();
                    return ResponseEntity.status(201).body(lutadores);
                }else{
                    return ResponseEntity.status(400).body("Ambos os lutadores devem estar vivos!");
                }
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
