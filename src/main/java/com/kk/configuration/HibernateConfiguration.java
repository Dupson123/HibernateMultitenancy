package com.kk.configuration;

import com.kk.multitenancy.CurrentTenantIdentifierResolverImpl;
import com.kk.multitenancy.MultiTenantConnectionProviderImpl;
import com.kk.service.SchemaService;
import com.kk.service.impl.SchemaServiceImpl;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author kkluz
 */
@Configuration
@EnableTransactionManagement
@ComponentScan( { "com.kk.configuration" } )
@PropertySource( value = "classpath:application.properties" )
public class HibernateConfiguration
{

    @Autowired
    Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource( dataSource() );
        sessionFactory.setPackagesToScan( "com.kk.model" );
        sessionFactory.setHibernateProperties( hibernateProperties() );
        sessionFactory.setMultiTenantConnectionProvider( connectionProvider() );
        sessionFactory.setCurrentTenantIdentifierResolver( resolver() );
        return sessionFactory;
    }

    @Bean
    public MultiTenantConnectionProvider connectionProvider()
    {
        MultiTenantConnectionProviderImpl connectionProvider = new MultiTenantConnectionProviderImpl();
        return connectionProvider;
    }

    @Bean
    public CurrentTenantIdentifierResolver resolver()
    {
        CurrentTenantIdentifierResolverImpl resolver = new CurrentTenantIdentifierResolverImpl();
        return resolver;
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( environment.getRequiredProperty( "jdbc.driverClassName" ) );
        dataSource.setUrl( environment.getRequiredProperty( "jdbc.url" ) );
        dataSource.setUsername( environment.getRequiredProperty( "jdbc.username" ) );
        dataSource.setPassword( environment.getRequiredProperty( "jdbc.password" ) );
        return dataSource;
    }

    private Properties hibernateProperties()
    {
        Properties properties = new Properties();
        properties.put( "hibernate.dialect", environment.getRequiredProperty( "hibernate.dialect" ) );
        properties.put( "hibernate.show_sql", environment.getRequiredProperty( "hibernate.show_sql" ) );
        properties.put( "hibernate.format_sql", environment.getRequiredProperty( "hibernate.format_sql" ) );
        properties.put( "hibernate.multiTenancy", MultiTenancyStrategy.SCHEMA );
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManagers( SessionFactory sessionFactory )
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory( sessionFactory );
        return transactionManager;
    }

    @Bean
    public SchemaService schemaService(){
        SchemaService schemaService = new SchemaServiceImpl();
        schemaService.setSchemaName( environment.getRequiredProperty( "default.schema.name" ) );
        return schemaService;
    }
}
