package br.com.mayconvyctor.visitor_package_management.repository;

import br.com.mayconvyctor.visitor_package_management.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    // Query Method customizado: O Spring gera um SELECT para buscar
    // todas as encomendas baseadas no ID de um morador específico.
    Iterable<Encomenda> findByMoradorId(Long moradorId);
}