package br.com.mayconvyctor.visitor_package_management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ENCOMENDAS")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao; // Ex: "Caixa Amazon", "Pacote Sedex"

    @Column(nullable = false)
    private LocalDateTime dataRecebimento;

    /**
     * @ManyToOne: Este é o coração dos bancos relacionais no JPA.
     * Significa "Muitas encomendas podem pertencer a Um morador".
     * @JoinColumn: Cria a chave estrangeira (Foreign Key) na tabela TB_ENCOMENDAS,
     * apontando para o ID da tabela TB_MORADORES.
     */
    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    @PrePersist
    protected void onCreate() {
        this.dataRecebimento = LocalDateTime.now();
    }

    // Construtor vazio obrigatório da JPA
    public Encomenda() {
    }

    // ------------------------------------------------------------------------
    // GETTERS e SETTERS
    // ------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
}