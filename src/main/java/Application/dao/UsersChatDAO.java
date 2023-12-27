package Application.dao;

import Application.model.UsersChat;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersChatDAO {
    private Session session = HibernateSessionFactoryUtil.getSessionFactory();


    public boolean save(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(UsersChat user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsersChat findId(long id) {
        try {
            UsersChat usersChat = session.get(UsersChat.class, id);
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UsersChat> findAll() {
        try {
            List<UsersChat> users = session.createQuery("select u from UsersChat u", UsersChat.class).getResultList();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean findChatIdBoolean(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId");
            query.setParameter("chatId", chatId);
            return query.getResultList().size() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsersChat findChatIdUsersChat(String chatId) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.chatID = :chatId");
            query.setParameter("chatId", chatId);
            UsersChat usersChat = (UsersChat) query.getSingleResult();
            return usersChat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean findPhoneNumber(String phoneNumber) {
        try {
            Query query = session.createQuery("select u from UsersChat u where u.phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            return query.getResultList().size() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
