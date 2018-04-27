package pl.jawegiel.todohibernate;


import java.util.List;

public class TaskService {

    private static TaskDao taskDao;

    public TaskService() {
        taskDao = new TaskDao();
    }


    public void persist(Task entity) {
        taskDao.openCurrentSessionwithTransaction();
        taskDao.persist(entity);
        taskDao.closeCurrentSessionwithTransaction();
    }

    public void update(Task entity) {
        taskDao.openCurrentSessionwithTransaction();
        taskDao.update(entity);
        taskDao.closeCurrentSessionwithTransaction();
    }

    public Task findById(int id) {
        taskDao.openCurrentSession();
        Task book = taskDao.findById(id);
        taskDao.closeCurrentSession();
        return book;
    }

    public int findLastId(List<Task> tasks) {
        taskDao.openCurrentSession();
        int id = taskDao.findLastId(tasks);
        taskDao.closeCurrentSession();
        return id;
    }

    public List<Task> getTasksForSpecifiedDay()
    {
        taskDao.openCurrentSession();
        List<Task> allTasks = taskDao.getAllTasks();
        taskDao.closeCurrentSession();
        return allTasks;
    }


    public List<Task> getAllTasks()
    {
        taskDao.openCurrentSession();
        List<Task> allTasks = taskDao.getAllTasks();
        taskDao.closeCurrentSession();
        return allTasks;
    }

        public void delete(int id) {
        taskDao.openCurrentSessionwithTransaction();
        Task task = taskDao.findById(id);
        taskDao.delete(task);
        taskDao.closeCurrentSessionwithTransaction();
    }
}