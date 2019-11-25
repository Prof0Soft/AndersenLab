package by.andersenlab.hibernate.crud.impl;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.hibernate.HibernateUtilImpl;
import by.andersenlab.hibernate.crud.Delete;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Data
public class DeleteImpl implements Delete {
    private HibernateUtil hibernateUtil;

    public void deleteOrder(Order order) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
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
