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

/**
 * @Named: Diz ao Spring e ao JSF que esta classe está disponível para ser acessada
 * diretamente pelas telas (.xhtml) com o nome "moradorBean".
 */
@Named
/**
 * @ViewScoped: O tempo de vida dessa classe. Os dados aqui dentro vão existir
 * apenas enquanto o usuário estiver com a tela de Moradores aberta. Se ele for para
 * a tela de Visitantes, essa classe é destruída para liberar memória RAM.
 */
@ViewScoped
public class MoradorBean implements Serializable {

    private final MoradorService moradorService;

    // A lista que vai alimentar a tabela do PrimeFaces na tela
    private List<Morador> moradores;

    // O objeto que vai receber os dados do formulário quando digitarmos na tela
    private Morador morador;

    // Injeção de dependência via construtor (Clean Code)
    public MoradorBean(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    /**
     * @PostConstruct: Assim que o usuário abrir a tela, o Spring constrói essa classe
     * e imediatamente chama este método ANTES da tela carregar de fato.
     * Usamos isso para buscar os dados no banco e deixar tudo pronto para exibição.
     */
    @PostConstruct
    public void init() {
        moradores = moradorService.listarTodos(); // Busca os dados no Oracle
        morador = new Morador(); // Prepara um objeto vazio para o formulário
    }

    // Método chamado quando clicarmos no botão "Novo" na tela
    public void novo() {
        this.morador = new Morador();
    }

    // Método chamado quando clicarmos no botão "Salvar" na tela
    public void salvar() {
        try {
            moradorService.salvar(morador); // Tenta salvar usando as regras de negócio

            // Se der certo, envia uma mensagem verde de sucesso para a tela (PrimeFaces Growl)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Morador salvo com sucesso!"));

            init(); // Recarrega a lista para mostrar o novo morador e limpa o formulário

        } catch (IllegalArgumentException e) {
            // Se o CPF for repetido (regra que fizemos no Service), lança uma mensagem vermelha de erro
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
    }

    // Método chamado quando clicarmos no botão "Excluir" na tabela
    public void excluir(Morador moradorSelecionado) {
        try {
            moradorService.excluirPorId(moradorSelecionado.getId());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Morador excluído!"));
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível excluir."));
        }
    }

    // ------------------------------------------------------------------------
    // GETTERS e SETTERS (Obrigatórios para o JSF conseguir ler e escrever na tela)
    // ------------------------------------------------------------------------

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