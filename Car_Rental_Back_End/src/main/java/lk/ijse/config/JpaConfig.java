package lk.ijse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(basePackages = {"lk.ijse.repo"})
    public class JpaConfig {

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dc){
            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
            factoryBean.setDataSource(dc);
            factoryBean.setJpaVendorAdapter(jpaVendorAdapter());

            factoryBean.setPackagesToScan("lk.ijse.entity");

            return factoryBean;
        }


        @Bean
        public DataSource dataSource() throws NamingException {
            DriverManagerDataSource dataSource=new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/carDB?createDatabaseIfNotExist=true");
            dataSource.setUsername("root");
            dataSource.setPassword("1234");
            return dataSource;

        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter(){
            HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
            vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
            vendorAdapter.setDatabase(Database.MYSQL);
            vendorAdapter.setGenerateDdl(true);
            vendorAdapter.setShowSql(true);
            return  vendorAdapter;
        }

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
            return new JpaTransactionManager(emf);
        }

    }
