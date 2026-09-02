package br.com.mayconvyctor.visitor_package_management.controller;

import br.com.mayconvyctor.visitor_package_management.model.Morador;
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
public class MoradorBean implements Serializable {

    private final MoradorService moradorService;

    private List<Morador> moradores;

    private Morador morador;

    public MoradorBean(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    @PostConstruct
    public void init() {
        moradores = moradorService.listarTodos();
        morador = new Morador();
    }

    // Método chamado quando clicarmos no botão "Novo" na tela
    public void novo() {
        this.morador = new Morador();
    }

    // Método chamado quando clicarmos no botão "Salvar" na tela
    public void salvar() {
        try {
            moradorService.salvar(morador);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Morador salvo com sucesso"));

            init();

        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    // Método chamado quando clicarmos no botão "Excluir" na tabela
    public void excluir(Morador moradorSelecionado) {
        try {
            moradorService.excluirPorId(moradorSelecionado.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Morador excluído"));
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível excluir"));
        }
    }
    public List<Morador> getMoradores() {
        return moradores;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
}