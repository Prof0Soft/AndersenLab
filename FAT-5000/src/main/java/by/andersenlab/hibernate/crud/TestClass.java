package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestClass {
    private Class<User> ownClassType;

    public TestClass(Class<User> ownClassType) {
        this.ownClassType = ownClassType;
    }

    /*public List<User> getAll() {
        List<User> kk = new ArrayList<>();
        kk.add(new User(1, "fdff", null));
        kk.add(new User(1, "fdff", null));
        kk.add(new User(1, "fdff", null));
        return kk;
    }*/

    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (List<User>) session.createQuery("from " + ownClassType.getSimpleName(),
                    ownClassType).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}