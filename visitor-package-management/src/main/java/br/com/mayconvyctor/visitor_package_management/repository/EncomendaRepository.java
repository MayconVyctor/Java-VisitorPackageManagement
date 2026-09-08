package br.com.mayconvyctor.visitor_package_management.repository;

import br.com.mayconvyctor.visitor_package_management.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    Iterable<Encomenda> findByMoradorId(Long moradorId);
}