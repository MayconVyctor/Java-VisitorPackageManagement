package br.com.mayconvyctor.visitor_package_management.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    /**
     * @KafkaListener: Essa anotação é o coração do consumidor.
     * Ela diz ao Spring: "Fique escutando o tópico 'encomendas-topic'.
     * Toda vez que chegar uma mensagem lá, execute este método imediatamente."
     */
    @KafkaListener(topics = "encomendas-topic", groupId = "condo-group")
    public void escutarNotificacao(String mensagem) {
        // Aqui simularíamos o envio de um e-mail usando JavaMailSender ou uma API de SMS (Twilio)
        System.out.println("=========================================================");
        System.out.println("[CONSUMER] Processando notificação em segundo plano: ");
        System.out.println( mensagem);
        System.out.println("=========================================================");
    }
}