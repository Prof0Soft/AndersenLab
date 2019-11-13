package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Delete {

    public void deleteOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
