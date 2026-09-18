package br.com.mayconvyctor.visitor_package_management.config;

import br.com.mayconvyctor.visitor_package_management.model.Encomenda;
import br.com.mayconvyctor.visitor_package_management.model.Morador;
import br.com.mayconvyctor.visitor_package_management.model.Visitante;
import br.com.mayconvyctor.visitor_package_management.repository.EncomendaRepository;
import br.com.mayconvyctor.visitor_package_management.repository.MoradorRepository;
import br.com.mayconvyctor.visitor_package_management.repository.VisitanteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestDatabaseSeeder implements CommandLineRunner {

    private final MoradorRepository moradorRepository;
    private final EncomendaRepository encomendaRepository;
    private final VisitanteRepository visitanteRepository;

    public TestDatabaseSeeder(MoradorRepository moradorRepository,
                              EncomendaRepository encomendaRepository,
                              VisitanteRepository visitanteRepository) {
        this.moradorRepository = moradorRepository;
        this.encomendaRepository = encomendaRepository;
        this.visitanteRepository = visitanteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (moradorRepository.count() == 0) {
            System.out.println(" Inserindo dados iniciais de teste");

            Morador m1 = new Morador();
            m1.setNomeCompleto("Dan Soares");
            m1.setCpf("11122233344");
            m1.setApartamento("101A");
            m1.setTelefone("11999998888");

            Morador m2 = new Morador();
            m2.setNomeCompleto("Livia Ferraz");
            m2.setCpf("55566677788");
            m2.setApartamento("205B");
            m2.setTelefone("11977776666");

            moradorRepository.saveAll(Arrays.asList(m1, m2));

            Encomenda enc1 = new Encomenda();
            enc1.setDescricao("Notebook Dell (Sedex)");
            enc1.setMorador(m1);
            encomendaRepository.save(enc1);

            Visitante vis1 = new Visitante();
            vis1.setNomeCompleto("Maycon Vyctor");
            vis1.setRg("MG-12.345.678");
            vis1.setMoradorDestino(m2);
            visitanteRepository.save(vis1);

            System.out.println(" Dados de teste inseridos ");
        }
    }
}