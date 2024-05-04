## Desenvolvimento do Back-end com Spring Boot

Este repositório foi criado para o desenvolvimento do back-end utilizando spring boot para o desenvolvimento do API do 6° semestre.

## Pré-requisitos 🛠️
- **JDK 17 ou superior**
- **Maven**
- **Docker**
- **IntelliJ IDEA**

## Estrutura do projeto 📁
- **Server**: Contém os serviços responsáveis pelo armazenamento e gerenciamento de registros, além de outras funcionalidades relacionadas ao servidor.
- **Login**: Contém os serviços responsáveis pelo gerenciamento de usuários e autenticação;

## Para rodar a aplicação 🖥️
Para garantir o funcionamento adequado da aplicação, é necessário configurar e executar ambos os serviços, tanto o de servidor quanto o de login, simultaneamente.

### Recomendação:
Para uma melhor organização e eficiência, recomenda-se abrir as pastas separadas, como a pasta `login` em uma janela do IntelliJ IDEA e a pasta `server` em outra janela. Facilitando assim a gestão e execução dos serviços.

- Instalação das dependências **(server e login)**:
  - `mvn install`
- Subir os serviços do Docker **(server e login)**:
  - `docker-compose up`
- Rodar a aplicação **(server e login)**:
  - `mvn spring-boot:run`
