import task.SubTask;
import task.Epic;
import task.Task;
import taskManager.TaskManager;
import taskManager.Status;


public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        System.out.println("a. Получение списка всех задач.");
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');


        System.out.println("b. Удаление всех задач.");
        taskManager.removeAllTask(); //проверка вывода
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');



        System.out.println("d. Создание. Сам объект должен передаваться в качестве параметра." +  '\'' +
                "task1" +  '\'' +
                "task2" +  '\'' +
                "task3" +  '\'' +
                "epic5" +  '\'' +
                "epic6" +  '\'' +
                "epic7" +  '\'' +
                "SubTask8" +  '\'' +
                "SubTask9" +  '\'' +
                "SubTask10");

        Task task1 = new Task("Задача 1", Status.NEW);
        taskManager.createTask(task1);
        Task task2 = new Task("Задача 2", Status.IN_PROGRESS);
        taskManager.createTask(task2);
        Task task3 = new Task("Задача 3", Status.DONE);
        taskManager.createTask(task3);
        Epic epic5 = new Epic("Epic 1", Status.NEW);
        taskManager.createEpic(epic5);
        Epic epic6 = new Epic("Epic 2", Status.IN_PROGRESS);
        taskManager.createEpic(epic6);
        Epic epic7 = new Epic("Epic 3", Status.DONE);
        taskManager.createEpic(epic7);
        SubTask SubTask8 = new SubTask("Подзадача 1", Status.NEW, 7);
        taskManager.createSubTask(SubTask8);
        SubTask SubTask9 = new SubTask("Подзадача 2", Status.NEW, 5);
        taskManager.createSubTask(SubTask9);
        SubTask SubTask10 = new SubTask("Подзадача 3", Status.DONE, 6);
        taskManager.createSubTask(SubTask10);


        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');


        System.out.println("c. Получение по идентификатору.");
        System.out.println("Вывод на экран. Задача, id 1 - " + taskManager.taskForId(1));
        System.out.println("Вывод на экран. эпик, id 5 - " + taskManager.epicForId(5));
        System.out.println("Вывод на экран. Подзадача, id 8 - " + taskManager.SubTaskForId(8));


        System.out.println("Получение списка всех подзадач определённого эпика.");
        System.out.println("Список подзадач Epic'a №6:" + taskManager.tasksOfEpic(6));



        System.out.println("Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра." +
                '\'' + "task4");
        Task task4 = new Task("Задача 1.2", Status.DONE);
        taskManager.updateTask(1, task4);



        System.out.println("обновление статуса SubTask и Epic" +  '\'' + "SubTask11 , SubTask12");
        SubTask SubTask11 = new SubTask("Подзадача 8.2", Status.IN_PROGRESS, 5);
        SubTask SubTask12 = new SubTask("Подзадача 10.2", Status.IN_PROGRESS, 7);
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');


        System.out.println("Удаление по идентификатору." +  '\'' + "Task(2) , Epic(6) , SubTask(9)");
        taskManager.deleteTask(2);
        taskManager.deleteEpic(6);
        taskManager.deleteSubTask(9);
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');


    }
}