package br.com.mayconvyctor.visitor_package_management.service;

import br.com.mayconvyctor.visitor_package_management.model.Encomenda;
import br.com.mayconvyctor.visitor_package_management.producer.NotificacaoProducer;
import br.com.mayconvyctor.visitor_package_management.repository.EncomendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final NotificacaoProducer notificacaoProducer;

    public EncomendaService(EncomendaRepository encomendaRepository, NotificacaoProducer notificacaoProducer) {
        this.encomendaRepository = encomendaRepository;
        this.notificacaoProducer = notificacaoProducer;
    }

    @Transactional(readOnly = true)
    public List<Encomenda> listarTodas() {
        return encomendaRepository.findAll();
    }

    @Transactional
    public Encomenda registrar(Encomenda encomenda) {
        Encomenda encomendaSalva = encomendaRepository.save(encomenda);
        String mensagem = String.format(
                "NOVA ENCOMENDA: '%s' recebida na portaria para o Morador %s (Apto: %s).",
                encomendaSalva.getDescricao(),
                encomendaSalva.getMorador().getNomeCompleto(),
                encomendaSalva.getMorador().getApartamento()
        );
        notificacaoProducer.enviarNotificacao(mensagem);

        return encomendaSalva;
    }
}