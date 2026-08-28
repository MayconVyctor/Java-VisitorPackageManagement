package br.com.mayconvyctor.visitor_package_management.controller;

import br.com.mayconvyctor.visitor_package_management.model.Encomenda;
import br.com.mayconvyctor.visitor_package_management.model.Morador;
import br.com.mayconvyctor.visitor_package_management.service.EncomendaService;
import br.com.mayconvyctor.visitor_package_management.service.MoradorService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EncomendaBean implements Serializable {

    private final EncomendaService encomendaService;
    private final MoradorService moradorService;

    private List<Encomenda> encomendas;
    private List<Morador> moradoresDisponiveis; // Para o dropdown da tela
    private Encomenda encomenda;
    private Long moradorIdSelecionado; // Guarda o ID do morador escolhido no dropdown

    // Injeção de ambas as dependências
    public EncomendaBean(EncomendaService encomendaService, MoradorService moradorService) {
        this.encomendaService = encomendaService;
        this.moradorService = moradorService;
    }

    @PostConstruct
    public void init() {
        encomendas = encomendaService.listarTodas();
        moradoresDisponiveis = moradorService.listarTodos(); // Preenche as opções do Select
        encomenda = new Encomenda();
    }

    public void novo() {
        this.encomenda = new Encomenda();
        this.moradorIdSelecionado = null;
    }

    public void salvar() {
        try {
            // Relaciona o morador escolhido na tela com a nova encomenda
            Morador moradorDestino = moradoresDisponiveis.stream()
                    .filter(m -> m.getId().equals(moradorIdSelecionado))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Morador inválido."));

            encomenda.setMorador(moradorDestino);

            // O Service vai salvar no Oracle e disparar para o Kafka!
            encomendaService.registrar(encomenda);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Encomenda registrada e notificação enviada!"));

            init(); // Recarrega a tabela

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    // Getters e Setters
    public List<Encomenda> getEncomendas() { return encomendas; }
    public List<Morador> getMoradoresDisponiveis() { return moradoresDisponiveis; }
    public Encomenda getEncomenda() { return encomenda; }
    public void setEncomenda(Encomenda encomenda) { this.encomenda = encomenda; }
    public Long getMoradorIdSelecionado() { return moradorIdSelecionado; }
    public void setMoradorIdSelecionado(Long moradorIdSelecionado) { this.moradorIdSelecionado = moradorIdSelecionado; }
}