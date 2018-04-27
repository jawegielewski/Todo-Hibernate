package pl.jawegiel.todohibernate;

public interface TaskDaoInterface {

    void persist(Task entity);

    void update(Task entity);

    void delete(Task entity);

}
