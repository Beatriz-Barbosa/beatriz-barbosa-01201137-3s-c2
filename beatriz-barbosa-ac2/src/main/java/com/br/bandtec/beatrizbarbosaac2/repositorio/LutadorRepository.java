package com.br.bandtec.beatrizbarbosaac2.repositorio;

import com.br.bandtec.beatrizbarbosaac2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador,Integer> {

    @Query("SELECT COUNT(l) FROM Lutador l WHERE l.isVivo=true")
    long countByIsVivo();

    @Query("SELECT l FROM Lutador l ORDER BY l.forcaGolpe DESC")
    List<Lutador> orderByForcaGolpe();

    @Query("SELECT l FROM Lutador l WHERE l.id=?1")
    Lutador getLutadorById(int id);

}
