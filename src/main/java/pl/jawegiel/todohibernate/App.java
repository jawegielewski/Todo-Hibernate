package pl.jawegiel.todohibernate;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class App {

    private static TaskService taskService = new TaskService();
    private static Timer timer = null;
    static List<Task> taskList = new ArrayList<>();

    public static void main(String... args) {


        taskList = taskService.getAllTasks();
        Clock clock = new Clock();

        for(Task t : taskList) {
            t.register(clock);
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            String var = sc.next();





            // if 'l' clicked - listening starts
            if (var.equalsIgnoreCase("l")) {
                timer = new Timer();
                timer.schedule(clock, 0, 1000);
            }

            // if 'a' clicked - add entity (task)
            if (var.equalsIgnoreCase("a")) {
                if (timer != null) timer.cancel();
                int lastId = taskService.findLastId(taskList);

                System.out.println("Enter the date [yyyy-MM-dd HH:mm]:");
                Scanner sc2 = new Scanner(System.in);
                String dataTaska = sc2.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime inputDateTime = LocalDateTime.parse(dataTaska, formatter);

                System.out.println("Enter the name of task:");
                Scanner sc3 = new Scanner(System.in);
                String nazwaTaska = sc3.next();
                taskService.persist(new Task(lastId + 1, inputDateTime, nazwaTaska));
                System.out.println("Task has been added to database");
            }

            // if 'r' clicked - remove entity (task)
            if (var.equalsIgnoreCase("r")) {
                if (timer != null) timer.cancel();
                int iter = 1;
                System.out.println("List of tasks");
                for (Task t : taskList) {
                    System.out.println(iter + ". id: " + t.getTaskId() + ", data: " + t.getData() + ", info: " + t.getTaskInfo());
                    iter++;
                }
                System.out.println("Enter task's id you want to remove:");
                Scanner sc4 = new Scanner(System.in);
                String idTaska = sc4.next();
                taskService.delete(Integer.parseInt(idTaska));
                System.out.println("Task has been removed from database");
            }

            // if 'e' clicked - edit entity (task)
            if (var.equalsIgnoreCase("e")) {
                if (timer != null) timer.cancel();
                int iter = 1;
                System.out.println("List of tasks");
                for (Task t : taskList) {
                    System.out.println(iter + ". id: " + t.getTaskId() + ", data: " + t.getData() + ", info: " + t.getTaskInfo());
                    iter++;
                }
                System.out.println("Enter task's id you want to edit:");
                Scanner sc5 = new Scanner(System.in);
                String idTaska = sc5.next();
                System.out.println("Enter new date of task [yyyy-MM-dd HH:mm]:");
                Scanner sc6 = new Scanner(System.in);
                String dataTaska = sc6.nextLine();
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime inputDateTime2 = LocalDateTime.parse(dataTaska, formatter2);


                System.out.println("Enter new info of task:");
                Scanner sc7 = new Scanner(System.in);
                String infoTaska = sc7.next();

                taskService.update(new Task(Integer.parseInt(idTaska), inputDateTime2, infoTaska));
                System.out.println("Task has been edited");
            }

            // if 'd' - list of entities (tasks) for specified day
            if (var.equalsIgnoreCase("d")) {
                if (timer != null) timer.cancel();

                int iter = 1;
                List<Integer> years = new ArrayList<>();
                List<Month> months = new ArrayList<>();
                List<Integer> days = new ArrayList<>();

                System.out.println("List of tasks");
                for (Task t : taskList) {
                    years.add(t.getData().getYear());
                    months.add(t.getData().getMonth());
                    days.add(t.getData().getDayOfMonth());
                    System.out.println(iter + ". id: " + t.getTaskId() + ", data: " + t.getData() + ", info: " + t.getTaskInfo());
                    iter++;
                }
                System.out.println("Enter the day you want to show tasks from");
                System.out.println("Enter the year [yyyy]:");
                Scanner sc8 = new Scanner(System.in);
                String year = sc8.next();
                System.out.println("Enter the month [MM]:");
                Scanner sc9 = new Scanner(System.in);
                String month = sc9.next();
                System.out.println("Enter the day [dd]:");
                Scanner sc10 = new Scanner(System.in);
                String day = sc10.next();

                for (Task t : taskList) {
                    if
                            (Integer.parseInt(year) == t.getData().getYear() &&
                            Month.of(Integer.parseInt(month)) == t.getData().getMonth() &&
                            Integer.parseInt(day) == t.getData().getDayOfMonth())
                        System.out.println(t.getTaskId());
                }
            }
        }
    }
}