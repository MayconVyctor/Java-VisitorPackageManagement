package br.com.mayconvyctor.visitor_package_management.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Service: Registra esta classe como um componente de serviço do Spring.
 */
@Service
public class NotificacaoProducer {

    // KafkaTemplate é a ferramenta do Spring que facilita o envio de mensagens
    private final KafkaTemplate<String, String> kafkaTemplate;

    // O nome do "canal" ou "fila" onde as mensagens serão publicadas
    private static final String TOPICO_NOTIFICACOES = "encomendas-topic";

    // Injeção de dependência via construtor (Clean Code)
    public NotificacaoProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Método que será chamado quando uma encomenda for registrada na portaria.
     */
    public void enviarNotificacao(String mensagem) {
        System.out.println("🚀 [PRODUCER] Enviando mensagem para o Kafka: " + mensagem);
        kafkaTemplate.send(TOPICO_NOTIFICACOES, mensagem);
    }
}