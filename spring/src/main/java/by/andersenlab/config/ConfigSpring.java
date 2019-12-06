package by.andersenlab.config;

import by.andersenlab.hibernate.util.HibernateUtil;
import by.andersenlab.hibernate.util.impl.HibernateUtilImpl;
import by.andersenlab.hibernate.crud.Create;
import by.andersenlab.hibernate.crud.Delete;
import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.hibernate.crud.Update;
import by.andersenlab.hibernate.crud.impl.CreateImpl;
import by.andersenlab.hibernate.crud.impl.DeleteImpl;
import by.andersenlab.hibernate.crud.impl.ReadImpl;
import by.andersenlab.hibernate.crud.impl.UpdateImpl;
import by.andersenlab.service.TravelService;
import by.andersenlab.service.UserService;
import by.andersenlab.service.impl.TravelServiceImpl;
import by.andersenlab.service.impl.UserServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@ComponentScan
public class ConfigSpring {

    @Bean
    @Profile(value = "!dev")
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
    public TravelService travelService() {
        return new TravelServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImp();
    }

    @Bean
    public HibernateUtil hibernateUtil() {
        return new HibernateUtilImpl();
    }
}
