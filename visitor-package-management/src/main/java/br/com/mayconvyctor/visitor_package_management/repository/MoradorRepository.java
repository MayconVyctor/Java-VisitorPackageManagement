package br.com.mayconvyctor.visitor_package_management.repository;

import br.com.mayconvyctor.condomanager.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Repository: Diz ao Spring que esta interface é um componente focado em
 * acesso a dados (Data Access Object - DAO). O Spring vai gerenciar isso na memória.
 */
@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

    /**
     * EXTENDENDO O JpaRepository:
     * Ao herdar de JpaRepository<Morador, Long>, nós ganhamos de graça (sem escrever uma
     * linha de código) métodos como: save(), findAll(), findById(), deleteById().
     * O 'Morador' é a tabela alvo, e o 'Long' é o tipo da Chave Primária (ID).
     */

    /**
     * QUERY METHODS (A mágica do Spring Data):
     * Nós podemos criar buscas customizadas apenas escrevendo o nome do método no padrão correto.
     * O Spring lê "findByCpf" e gera automaticamente um: "SELECT * FROM TB_MORADORES WHERE cpf = ?"
     *
     * Retornamos um Optional<> pois é a melhor prática (Clean Code) para buscas
     * que podem não encontrar resultados. Isso evita o temido NullPointerException.
     */
    Optional<Morador> findByCpf(String cpf);

    /**
     * Retorna uma lista de moradores pelo número do apartamento.
     */
    Iterable<Morador> findByApartamento(String apartamento);
}
