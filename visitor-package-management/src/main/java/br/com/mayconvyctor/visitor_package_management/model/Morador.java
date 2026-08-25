package br.com.mayconvyctor.condomanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * @Entity: Diz ao Spring/Hibernate que esta classe Java vai se transformar
 * em uma tabela no banco de dados Oracle.
 */
@Entity
/**
 * @Table: Permite customizar o nome da tabela no banco. É uma boa prática
 * colocar o nome no plural e em maiúsculo, padrão comum em bancos Oracle (TB_MORADORES).
 */
@Table(name = "TB_MORADORES")
public class Morador {

    /**
     * @Id: Define que este campo é a Chave Primária (Primary Key) da tabela.
     * @GeneratedValue: O banco de dados vai gerar esse ID automaticamente.
     * GenerationType.IDENTITY: É a estratégia mais eficiente, onde o próprio banco
     * gerencia o auto-incremento (no Oracle 12c+ isso usa colunas Identity).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column: Customiza a coluna no banco.
     * nullable = false garante que ninguém consiga salvar um morador sem nome (segurança de dados).
     * length = 100 limita o tamanho para economizar espaço e evitar abusos.
     */
    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    // unique = true impede que dois moradores tenham o mesmo documento cadastrado.
    @Column(nullable = false, unique = true, length = 20)
    private String cpf;

    @Column(nullable = false, length = 10)
    private String apartamento;

    @Column(nullable = false, length = 15)
    private String telefone;

    // Campo para auditoria (saber quando o registro foi criado)
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    /**
     * @PrePersist: Esta é uma anotação de ciclo de vida da JPA.
     * Antes de salvar um novo Morador no banco de dados, o Hibernate vai
     * executar este método automaticamente. É a melhor prática para preencher
     * datas de criação, tirando essa responsabilidade de quem chamou a classe.
     */
    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }

    // ------------------------------------------------------------------------
    // CONSTRUTORES, GETTERS E SETTERS
    // ------------------------------------------------------------------------

    // A JPA exige um construtor vazio obrigatório
    public Morador() {
    }

    // Gerar os Getters e Setters para todos os campos (menos o setter do ID e dataCadastro,
    // pois eles são gerenciados pelo banco/sistema, o que aumenta o encapsulamento).

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}