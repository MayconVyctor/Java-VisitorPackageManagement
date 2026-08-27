# Java-VisitorPackageManagement

> Um sistema robusto e full-stack de gerenciamento de condomínios, desenvolvido para otimizar o controle de acesso de visitantes, o acompanhamento de encomendas e o gerenciamento de moradores.

## Sobre o Projeto

Java-VisitorPackageManagement é uma aplicação web desenvolvida para modernizar as operações diárias de edifícios residenciais. Ela oferece uma maneira segura e eficiente de gerenciar o fluxo de pessoas e mercadorias, garantindo um ambiente colaborativo e seguro para moradores e funcionários.

Este projeto foi desenvolvido com foco em padrões de arquitetura de nível empresarial, utilizando um backend Java robusto, transmissão de eventos em tempo real e uma interface de usuário baseada em componentes.

## Tecnologias

* **Backend:** Java 17, Spring Boot 3
* **Persistência de Dados:** Spring Data JPA, Oracle DB
* **Segurança:** Spring Security (Controle de Acesso Baseado em Funções)
* **Mensageria / Transmissão de Eventos:** Apache Kafka
* **Frontend:** JSF (Jakarta Server Faces), PrimeFaces, JoinFaces
* **Infraestrutura:** Docker e Docker Compose

## Principais Funcionalidades

* **Dashboards Baseados em Funções:** Interfaces e permissões distintas para Administradores (Gerentes) e Porteiros.
* **Gerenciamento de Moradores:** CRUD completo para apartamentos, moradores e informações de contato.
* **Controle de Acesso de Visitantes:** Registro em tempo real das entradas e saídas de visitantes.
* **Notificações de Encomendas:** Arquitetura orientada a eventos utilizando **Kafka** para processar de forma assíncrona e notificar os moradores quando as encomendas chegam à portaria.
* **Interface Moderna:** Componentes responsivos e interativos desenvolvidos com PrimeFaces.

## Como Começar

### Pré-requisitos

* Java 17 ou superior
* Maven
* Docker e Docker Compose (para executar o Oracle DB e o Kafka localmente)

### Instalação e Configuração (no Linux)

1. **Clone o repositório:**

   ```bash
   git clone [https://github.com/MayconVyctor/Java-VisitorPackageManagement.git](https://github.com/MayconVyctor/Java-VisitorPackageManagement.git)
   cd Java-VisitorPackageManagement
   ```

2. **Inicie a infraestrutura (Oracle DB e Kafka):**

   ```bash
   docker-compose up -d
   ```

3. **Compile a aplicação:**

   ```bash
   mvn clean install
   ```

4. **Execute a aplicação:**

   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a aplicação:**
   Abra seu navegador e acesse `http://localhost:8080`

## Contribuição

Contribuições, relatos de problemas e solicitações de novas funcionalidades são bem-vindos! Fique à vontade para consultar a [página de issues](https://github.com/MayconVyctor/Java-VisitorPackageManagement/issues).

## Licença

Este projeto é licenciado sob a licença [MIT](https://choosealicense.com/licenses/mit/).
