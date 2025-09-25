package co.edu.uptc.temufacebook.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableNeo4jRepositories(basePackages = "co.edu.uptc.temufacebook.persistence.crudRepository.neo4jRepositories")
@EnableTransactionManagement
public class Neo4jConfig extends AbstractNeo4jConfig {

    @Value("${spring.neo4j.uri:bolt://localhost:7687}")
    private String uri;

    @Value("${spring.neo4j.authentication.username:neo4j}")
    private String username;

    @Value("${spring.neo4j.authentication.password:neo4jPass}")
    private String password;

    @Bean
    @Override
    public Driver driver() {
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    @Bean
    @Override
    public PlatformTransactionManager transactionManager(Driver driver, DatabaseSelectionProvider databaseNameProvider) {
        return new Neo4jTransactionManager(driver, databaseNameProvider);
    }
    
}
