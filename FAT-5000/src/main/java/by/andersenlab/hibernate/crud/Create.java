package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.Country;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Create<T> {

    public T insertNewObject(T ob) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(ob);
            transaction.commit();
            session.close();

            return ob;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public User insertNewUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();

            return new Read().getUser(user.getNameUser());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public Order insertNewOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    public Country insertCountry(Country country) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}