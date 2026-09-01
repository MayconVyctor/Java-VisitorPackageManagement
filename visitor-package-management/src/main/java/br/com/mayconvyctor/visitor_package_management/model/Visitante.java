package br.com.mayconvyctor.visitor_package_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_VISITANTES")
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, length = 20)
    private String rg; // RG é mais comum para controle de portaria que CPF

    @Column(nullable = false)
    private LocalDateTime dataEntrada;

    // A hora de saída começa nula, e o porteiro preenche quando a pessoa vai embora
    private LocalDateTime dataSaida;

    /**
     * Relacionamento: Todo visitante vai para o apartamento de um Morador específico.
     */
    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador moradorDestino;

    @PrePersist
    protected void onCreate() {
        this.dataEntrada = LocalDateTime.now();
    }

    public Visitante() {
    }

    public Long getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    public LocalDateTime getDataEntrada() { return dataEntrada; }
    public LocalDateTime getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDateTime dataSaida) { this.dataSaida = dataSaida; }
    public Morador getMoradorDestino() { return moradorDestino; }
    public void setMoradorDestino(Morador moradorDestino) { this.moradorDestino = moradorDestino; }
}