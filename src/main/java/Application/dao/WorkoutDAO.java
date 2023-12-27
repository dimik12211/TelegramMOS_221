package Application.dao;

import Application.model.UsersChat;
import Application.model.Workout;
import Application.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutDAO {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    public boolean save(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(workout);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(workout);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Workout workout) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(workout);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Workout findId(long id) {
        try {
            Workout workout = session.get(Workout.class, id);
            return workout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Workout> findAll() {
        try {
            List<Workout> workouts = session.createQuery("select u from Workout u", Workout.class).getResultList();
            return workouts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
