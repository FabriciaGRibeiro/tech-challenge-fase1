# Conexão H2 em memória (para testes)
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver spring.datasource.username=sa
spring.datasource.password= # Para que o Hibernate crie e remova o esquema em
cada teste spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect # Desabilitar logs
SQL nos testes (opcional) spring.jpa.show-sql=false
