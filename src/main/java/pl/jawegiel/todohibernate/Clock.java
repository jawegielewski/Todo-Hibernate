package pl.jawegiel.todohibernate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Clock extends TimerTask implements Observer {



    // list of tasks from DB
    private List<Task> tasks = App.taskList;
    public List<Task> getTasks() { return tasks; }

    // list of coming tasks ids, protects from multiple call taskComes() at every second
    private List<Integer> comingTasksId = new ArrayList<>();
    public List<Integer> getComingTasksId() { return comingTasksId; }

    // list of tasks to do
    private int tasksToDo=0;
    public int getTasksToDo() { return tasksToDo; }





    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("h:"+now.getMinute()+" m:"+now.getMinute() + " s:" + now.getSecond());
        for (Task t : tasks) {

           //checkDates() && difference in minutes between now and task's date less than 30 && prevent from reverse call
             if (checkDates(now, t.getData()) && Duration.between(now, t.getData()).toMinutes() < 30 && Duration.between(now, t.getData()).toMinutes() > 0) {

                    if (!comingTasksId.contains(t.getTaskId())) {
                        t.taskComes(t.getTaskId());
                    }
                    comingTasksId.add(t.getTaskId());
            }
        }
    }


    @Override
    public void update() {
        tasksToDo++;
        System.out.println("You have "+tasksToDo+" tasks to do");
    }




    //checks if the object's fields of now (without minutes) cover the fields of task's data
    private boolean checkDates(LocalDateTime now, LocalDateTime taskDate) {
        return (now.getYear() == taskDate.getYear() &&
                now.getMonth() == taskDate.getMonth() &&
                now.getDayOfMonth() == taskDate.getDayOfMonth());
    }
}
