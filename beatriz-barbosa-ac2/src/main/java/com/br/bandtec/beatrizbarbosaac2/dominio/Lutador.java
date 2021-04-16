package com.br.bandtec.beatrizbarbosaac2.dominio;

import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Lutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3,max = 12)
    private String nome;

    @Positive
    private Double forcaGolpe;

    private Double vida = 100.0;

    private Integer concentracoesRealizadas = 0;

    private Boolean isVivo = true;

    public ResponseEntity concentrar(){
        if (concentracoesRealizadas > 3){
            return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
        }else {
            this.vida = this.vida * 1.5;
            concentracoesRealizadas++;
            return ResponseEntity.ok().build();
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public Double getVida() {

        return vida;
    }

    public Integer getConcentracoesRealizadas() {
        return concentracoesRealizadas;
    }

    public Boolean getIsvivo() {
        return vida > 0? true: false;
    }

    public void setVida(Double vida) {
        if(vida < 0){
            vida = 0.0;
        }
        this.vida = vida;
    }
}
