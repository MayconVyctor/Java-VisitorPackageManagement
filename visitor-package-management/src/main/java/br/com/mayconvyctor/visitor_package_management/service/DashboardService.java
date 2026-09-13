package br.com.mayconvyctor.visitor_package_management.service;

import br.com.mayconvyctor.visitor_package_management.repository.EncomendaRepository;
import br.com.mayconvyctor.visitor_package_management.repository.MoradorRepository;
import br.com.mayconvyctor.visitor_package_management.repository.VisitanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

    private final MoradorRepository moradorRepository;
    private final EncomendaRepository encomendaRepository;
    private final VisitanteRepository visitanteRepository;

    public DashboardService(MoradorRepository moradorRepository,
                            EncomendaRepository encomendaRepository,
                            VisitanteRepository visitanteRepository) {
        this.moradorRepository = moradorRepository;
        this.encomendaRepository = encomendaRepository;
        this.visitanteRepository = visitanteRepository;
    }

    @Transactional(readOnly = true)
    public long getTotalMoradores() {
        return moradorRepository.count();
    }

    @Transactional(readOnly = true)
    public long getTotalEncomendas() {
        return encomendaRepository.count();
    }

    @Transactional(readOnly = true)
    public long getVisitantesNoLocal() {
        // Conta o tamanho da lista de visitantes que ainda não registraram saída
        return visitanteRepository.findByDataSaidaIsNull().size();
    }
}