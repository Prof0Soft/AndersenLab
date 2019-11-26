package by.andersenlab.config;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.hibernate.HibernateUtilImpl;
import by.andersenlab.hibernate.crud.Create;
import by.andersenlab.hibernate.crud.Delete;
import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.hibernate.crud.Update;
import by.andersenlab.hibernate.crud.impl.CreateImpl;
import by.andersenlab.hibernate.crud.impl.DeleteImpl;
import by.andersenlab.hibernate.crud.impl.ReadImpl;
import by.andersenlab.hibernate.crud.impl.UpdateImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.andersenlab")
public class ConfigSpring {
    @Bean
    public Read read() {
        return new ReadImpl();
    }

    @Bean
    public Create create() {
        return new CreateImpl();
    }

    @Bean
    public Delete delete() {
        return new DeleteImpl();
    }

    @Bean
    public Update update() {
        return new UpdateImpl();
    }

    @Bean
    public HibernateUtil hibernateUtil() {
        return new HibernateUtilImpl();
    }
}
