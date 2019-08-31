package pl.coderslab.workshop.twitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;


@Configuration
@ComponentScan(basePackages = {
        "pl.coderslab.workshop.twitter",

}, excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        value = EnableWebMvc.class
))
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.workshop.twitter.repositories")
public class AppConfig {


    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("twitterPU");
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager();
        return tm;

    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
