package by.andersenlab.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class HibernateUtilImpl implements HibernateUtil {
    private SessionFactory sessionFactory;

    public HibernateUtilImpl() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;

    }
}
