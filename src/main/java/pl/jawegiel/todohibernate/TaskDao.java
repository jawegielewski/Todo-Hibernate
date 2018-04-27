package pl.jawegiel.todohibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.Month;
import java.util.List;

public class TaskDao implements TaskDaoInterface {

    private Session currentSession;
    private Transaction currentTransaction;

    public Session getCurrentSession() { return currentSession; }


    public TaskDao() { }



    @Override
    public void persist(Task entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Task entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(Task entity) {
        getCurrentSession().delete(entity);
    }




    public Session openCurrentSession() {
        currentSession = HibernateUtil.buildSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.buildSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }





    public Task findById(int id) {
        Task task = (Task) getCurrentSession().get(Task.class, id);
        return task;
    }

    public int findLastId(List<Task> tasks) {
        int id=0;
        for(Task t : tasks)
        {
            if(id<t.getTaskId()) id=t.getTaskId();
        }
        return id;
    }

    public List<Task> getTasksForSpecifiedDay(int year, Month month, int dayOfMonth)
    {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        criteria.from(Task.class);

        return getCurrentSession().createQuery(criteria).getResultList();
    }

    public List<Task> getAllTasks()
    {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        criteria.from(Task.class);

        return getCurrentSession().createQuery(criteria).getResultList();
    }


}
