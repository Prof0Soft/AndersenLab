package by.andersenlab.hibernate.crud.impl;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.hibernate.crud.Create;
import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Data
public class CreateImpl implements Create {
    private HibernateUtil hibernateUtil;
    private Read read;

    public User insertNewUser(User user) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();

            return read.getUser(user.getNameUser());
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
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
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