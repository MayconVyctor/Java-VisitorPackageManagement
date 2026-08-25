package br.com.mayconvyctor.visitor_package_management.repository;

import br.com.mayconvyctor.condomanager.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {
    Optional<Morador> findByCpf(String cpf);
    Iterable<Morador> findByApartamento(String apartamento);
}
