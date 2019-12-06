package by.andersenlab.service;


import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.hibernate.crud.impl.ReadImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile(value = "!test")
@Configuration
@ComponentScan
public class ConfigMockSpringForTest {
    @Bean
    @Primary
    public Read read() {
        return Mockito.mock(ReadImpl.class);
    }

}
