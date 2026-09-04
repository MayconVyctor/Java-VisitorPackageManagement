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
    private List<Morador> moradoresDisponiveis;
    private Encomenda encomenda;
    private Long moradorIdSelecionado;

    public EncomendaBean(EncomendaService encomendaService, MoradorService moradorService) {
        this.encomendaService = encomendaService;
        this.moradorService = moradorService;
    }

    @PostConstruct
    public void init() {
        encomendas = encomendaService.listarTodas();
        moradoresDisponiveis = moradorService.listarTodos();
        encomenda = new Encomenda();
    }

    public void novo() {
        this.encomenda = new Encomenda();
        this.moradorIdSelecionado = null;
    }

    public void salvar() {
        try {
            Morador moradorDestino = moradoresDisponiveis.stream()
                    .filter(m -> m.getId().equals(moradorIdSelecionado))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Morador inválido."));

            encomenda.setMorador(moradorDestino);

            encomendaService.registrar(encomenda);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Encomenda registrada e notificação enviada!"));

            init();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    public List<Encomenda> getEncomendas() { return encomendas; }
    public List<Morador> getMoradoresDisponiveis() { return moradoresDisponiveis; }
    public Encomenda getEncomenda() { return encomenda; }
    public void setEncomenda(Encomenda encomenda) { this.encomenda = encomenda; }
    public Long getMoradorIdSelecionado() { return moradorIdSelecionado; }
    public void setMoradorIdSelecionado(Long moradorIdSelecionado) { this.moradorIdSelecionado = moradorIdSelecionado; }
}