package br.com.mayconvyctor.visitor_package_management.controller;

import br.com.mayconvyctor.visitor_package_management.model.Morador;
import br.com.mayconvyctor.visitor_package_management.model.Visitante;
import br.com.mayconvyctor.visitor_package_management.service.MoradorService;
import br.com.mayconvyctor.visitor_package_management.service.VisitanteService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VisitanteBean implements Serializable {

    private final VisitanteService visitanteService;
    private final MoradorService moradorService;

    private List<Visitante> visitantes;
    private List<Morador> moradoresDisponiveis;
    private Visitante visitante;
    private Long moradorIdSelecionado;

    public VisitanteBean(VisitanteService visitanteService, MoradorService moradorService) {
        this.visitanteService = visitanteService;
        this.moradorService = moradorService;
    }

    @PostConstruct
    public void init() {
        visitantes = visitanteService.listarTodos();
        moradoresDisponiveis = moradorService.listarTodos();
        visitante = new Visitante();
    }

    public void novo() {
        this.visitante = new Visitante();
        this.moradorIdSelecionado = null;
    }

    public void salvarEntrada() {
        try {
            Morador moradorDestino = moradoresDisponiveis.stream()
                    .filter(m -> m.getId().equals(moradorIdSelecionado))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Morador inválido."));

            visitante.setMoradorDestino(moradorDestino);
            visitanteService.registrarEntrada(visitante);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Entrada liberada!"));
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    public void registrarSaida(Visitante visitanteSelecionado) {
        try {
            visitanteService.registrarSaida(visitanteSelecionado.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Saída registrada."));
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    // Getters e Setters
    public List<Visitante> getVisitantes() { return visitantes; }
    public List<Morador> getMoradoresDisponiveis() { return moradoresDisponiveis; }
    public Visitante getVisitante() { return visitante; }
    public void setVisitante(Visitante visitante) { this.visitante = visitante; }
    public Long getMoradorIdSelecionado() { return moradorIdSelecionado; }
    public void setMoradorIdSelecionado(Long moradorIdSelecionado) { this.moradorIdSelecionado = moradorIdSelecionado; }
}