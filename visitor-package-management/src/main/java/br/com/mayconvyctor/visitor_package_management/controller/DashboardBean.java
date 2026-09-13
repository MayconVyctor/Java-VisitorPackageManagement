package br.com.mayconvyctor.visitor_package_management.controller;

import br.com.mayconvyctor.visitor_package_management.service.DashboardService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

    private final DashboardService dashboardService;

    private long totalMoradores;
    private long totalEncomendas;
    private long visitantesNoLocal;

    public DashboardBean(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostConstruct
    public void init() {
        this.totalMoradores = dashboardService.getTotalMoradores();
        this.totalEncomendas = dashboardService.getTotalEncomendas();
        this.visitantesNoLocal = dashboardService.getVisitantesNoLocal();
    }

    public long getTotalMoradores() { return totalMoradores; }
    public long getTotalEncomendas() { return totalEncomendas; }
    public long getVisitantesNoLocal() { return visitantesNoLocal; }
}