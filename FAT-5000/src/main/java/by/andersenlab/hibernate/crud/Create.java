package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Create {

    public User insertNewUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();

            return new Read().getUserByName(user.getNameUser());
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
}