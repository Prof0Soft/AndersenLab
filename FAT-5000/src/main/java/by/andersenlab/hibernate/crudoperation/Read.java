package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class Read {

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where nameUser = :id1", User.class);
            query.setParameter("id1", name);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order getOrderByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where nameOrder = :id1", Order.class);
            query.setParameter("id1", name);
            Object ob = query.getSingleResult();
            return ob != null ? (Order) ob : null;
        } catch (NoResultException e) {
//            e.printStackTrace();
            return null;
        }
    }
}
