package br.com.mayconvyctor.visitor_package_management.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPICO_NOTIFICACOES = "encomendas-topic";

    public NotificacaoProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarNotificacao(String mensagem) {
        System.out.println(" [PRODUCER] Enviando mensagem para o Kafka: " + mensagem);
        kafkaTemplate.send(TOPICO_NOTIFICACOES, mensagem);
    }
}