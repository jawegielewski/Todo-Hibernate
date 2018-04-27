package pl.jawegiel.todohibernate;

public interface Publisher {
    void register(Observer o);

    void unregister(Observer o);

    void notifyObservers();

}
