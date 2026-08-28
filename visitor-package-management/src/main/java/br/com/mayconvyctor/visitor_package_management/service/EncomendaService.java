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

    /**
     * Injeção de dependências via construtor.
     * Repare que o Service conversa com o Repository e com o Producer,
     * mantendo o isolamento (Clean Architecture).
     */
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
        // 1. Salva a encomenda no banco de dados Oracle
        Encomenda encomendaSalva = encomendaRepository.save(encomenda);

        // 2. Monta a mensagem que será enviada para o mensageiro
        String mensagem = String.format(
                "NOVA ENCOMENDA: '%s' recebida na portaria para o Morador %s (Apto: %s).",
                encomendaSalva.getDescricao(),
                encomendaSalva.getMorador().getNomeCompleto(),
                encomendaSalva.getMorador().getApartamento()
        );

        // 3. Dispara a notificação assíncrona para o Kafka
        notificacaoProducer.enviarNotificacao(mensagem);

        return encomendaSalva;
    }
}