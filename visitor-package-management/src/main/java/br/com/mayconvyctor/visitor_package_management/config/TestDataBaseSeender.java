package br.com.mayconvyctor.visitor_package_management.config;

import br.com.mayconvyctor.condomanager.model.Morador;
import br.com.mayconvyctor.condomanager.repository.MoradorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
@Configuration
public class TestDatabaseSeeder implements CommandLineRunner {

    private final MoradorRepository moradorRepository;
    public TestDatabaseSeeder(MoradorRepository moradorRepository) {
        this.moradorRepository = moradorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (moradorRepository.count() == 0) {
            System.out.println("🌱 Semeando banco de dados Oracle com moradores de teste...");

            Morador m1 = new Morador();
            m1.setNomeCompleto("João Silva");
            m1.setCpf("11122233344");
            m1.setApartamento("101A");
            m1.setTelefone("11999998888");

            Morador m2 = new Morador();
            m2.setNomeCompleto("Maria Oliveira");
            m2.setCpf("55566677788");
            m2.setApartamento("205B");
            m2.setTelefone("11977776666");

            moradorRepository.saveAll(Arrays.asList(m1, m2));

            System.out.println("✅ Moradores inseridos com sucesso!");
        } else {
            System.out.println("⚡ O banco já possui dados. Ignorando a semeadura (seed).");
        }
    }
}
