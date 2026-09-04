package br.com.mayconvyctor.visitor_package_management.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {
    @KafkaListener(topics = "encomendas-topic", groupId = "condo-group")
    public void escutarNotificacao(String mensagem) {
        System.out.println("");
        System.out.println("[CONSUMER] Processando notificação em segundo plano: ");
        System.out.println( mensagem);
        System.out.println("");
    }
}