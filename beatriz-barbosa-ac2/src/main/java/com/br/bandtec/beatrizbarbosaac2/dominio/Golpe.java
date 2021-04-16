package com.br.bandtec.beatrizbarbosaac2.dominio;

import javax.validation.constraints.Positive;

public class Golpe {

    @Positive
    private Integer idLutadorBate;

    @Positive
    private Integer idLutadorApanha;

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }
}
