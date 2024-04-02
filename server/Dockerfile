# Use uma imagem base que inclua o Java e outras dependências necessárias
FROM openjdk:17-jre-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR do seu servidor Spring Boot para o contêiner
COPY target/seu-aplicativo-spring-boot.jar /app/app.jar

# Expõe a porta em que o seu servidor Spring Boot será executado
EXPOSE 8080

# Comando para iniciar o servidor Spring Boot quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
