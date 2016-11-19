package ro.ucv.ace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.ucv.ace.model.IAuthenticatable;
import ro.ucv.ace.model.ISubgroup;
import ro.ucv.ace.model.IUser;
import ro.ucv.ace.model.impl.Subgroup;
import ro.ucv.ace.model.impl.User;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.impl.JpaRepository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * This class is used by the IoC Spring  container as a source for bean definitions. It contains the necessary beans
 * for JPA configuration.
 *
 * @author Georgian Vladutu
 */
@Configuration
@EnableTransactionManagement
@EnableSpringConfigured
@EnableScheduling
@ComponentScan({"ro.ucv.ace"})
@PropertySource(value = {"classpath:db.properties", "classpath:mail.properties"})
public class DomainConfig {

    @Autowired
    private Environment environment;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.import_files_sql_extractor",
                environment.getRequiredProperty("hibernate.hbm2ddl.import_files_sql_extractor"));

        return properties;
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ro.ucv.ace.*");
        em.setJpaProperties(hibernateProperties());
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setPersistenceUnitName("newPersistenceUnit");

        return em;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "authenticatableRepository")
    IJpaRepository<IAuthenticatable, User, Integer> authenticatableUserIntegerIJpaRepository() {
        return new JpaRepository<>(IAuthenticatable.class, User.class);
    }

    @Bean(name = "innerUserRepository")
    IJpaRepository<IUser, User, Integer> iUserUserIntegerIJpaRepository() {
        return new JpaRepository<>(IUser.class, User.class);
    }

    @Bean(name = "innerSubgroupRepository")
    IJpaRepository<ISubgroup, Subgroup, Integer> iSubgroupSubgroupIntegerIJpaRepository() {
        return new JpaRepository<>(ISubgroup.class, Subgroup.class);
    }
}
