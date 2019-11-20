package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.HibernateUtil;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Read<T> {
    private Class<T> ownClassType;

    public List<T> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (List<T>) session.createQuery("from " + ownClassType.getSimpleName(),
                    ownClassType).getResultList();
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
}