package br.com.mayconvyctor.visitor_package_management.service;

import br.com.mayconvyctor.visitor_package_management.model.Visitante;
import br.com.mayconvyctor.visitor_package_management.repository.VisitanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitanteService {

    private final VisitanteRepository visitanteRepository;

    public VisitanteService(VisitanteRepository visitanteRepository) {
        this.visitanteRepository = visitanteRepository;
    }

    @Transactional(readOnly = true)
    public List<Visitante> listarTodos() {
        return visitanteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Visitante> listarAtivos() {
        return visitanteRepository.findByDataSaidaIsNull();
    }

    @Transactional
    public Visitante registrarEntrada(Visitante visitante) {
        return visitanteRepository.save(visitante);
    }

    /**
     * Regra de Negócio: Busca o visitante pelo ID e preenche a data de saída
     * com o horário atual do servidor.
     */
    @Transactional
    public void registrarSaida(Long visitanteId) {
        Visitante visitante = visitanteRepository.findById(visitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Visitante não encontrado."));

        if (visitante.getDataSaida() != null) {
            throw new IllegalStateException("A saída deste visitante já foi registrada.");
        }

        visitante.setDataSaida(LocalDateTime.now());
        visitanteRepository.save(visitante);
    }
}