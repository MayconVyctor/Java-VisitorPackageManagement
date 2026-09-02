package br.com.mayconvyctor.visitor_package_management.repository;

import br.com.mayconvyctor.visitor_package_management.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Long> {

    /**
     * Query Method: O Spring gera um SELECT buscando todos os visitantes
     * onde a coluna 'data_saida' está nula (ou seja, ainda estão no prédio).
     */
    List<Visitante> findByDataSaidaIsNull();
}