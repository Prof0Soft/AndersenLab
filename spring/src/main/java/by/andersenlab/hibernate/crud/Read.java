package by.andersenlab.hibernate.crud;

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

    public User getUser(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where nameUser = :id1", User.class);
            query.setParameter("id1", name);
            return (User) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int idUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where idUser = :id1", User.class);
            query.setParameter("id1", idUser);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order getOrder(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where nameOrder = :id1", Order.class);
            query.setParameter("id1", name);
            Object ob = query.getSingleResult();
            return ob != null ? (Order) ob : null;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order getOrder(int idOrder) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where id = :id1", Order.class);
            query.setParameter("id1", idOrder);
            Object ob = query.getSingleResult();
            return ob != null ? (Order) ob : null;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order", Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}