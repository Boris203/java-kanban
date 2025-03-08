import task.SubTask;
import task.Epic;
import task.Task;
import manager.TaskManager;
import task.Status;


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
        System.out.println(taskManager.getTasks().toString() + '\n' +
                taskManager.getEpics().toString() + '\n' +
                taskManager.getSubTasks().toString() + '\n');



        System.out.println("d. Создание. Сам объект должен передаваться в качестве параметра." +  '\'' +
                "task1" +  '\n' +
                "task2" +  '\n' +
                "task3" +  '\n' +
                "epic5" +  '\n' +
                "epic6" +  '\n' +
                "epic7" +  '\n' +
                "SubTask8" +  '\n' +
                "SubTask9" +  '\n' +
                "SubTask10");

        Task task1 = new Task("Задача 1", "описание задачи", Status.NEW);
        taskManager.createTask(task1);
        Task task2 = new Task("Задача 2", "описание задачи", Status.IN_PROGRESS);
        taskManager.createTask(task2);
        Task task3 = new Task("Задача 3", "описание задачи", Status.DONE);
        taskManager.createTask(task3);
        Epic epic5 = new Epic("Epic 1", "описание задачи", Status.NEW);
        taskManager.createEpic(epic5);
        Epic epic6 = new Epic("Epic 2", "описание задачи", Status.IN_PROGRESS);
        taskManager.createEpic(epic6);
        Epic epic7 = new Epic("Epic 3", "описание задачи", Status.DONE);
        taskManager.createEpic(epic7);
        SubTask SubTask8 = new SubTask("Подзадача 1", "описание задачи", Status.NEW,
                6);
        taskManager.createSubTask(SubTask8);
        SubTask SubTask9 = new SubTask("Подзадача 2", "описание задачи", Status.NEW,
                5);
        taskManager.createSubTask(SubTask9);
        SubTask SubTask10 = new SubTask("Подзадача 3", "описание задачи", Status.DONE,
                4);
        taskManager.createSubTask(SubTask10);


        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\n' +
                taskManager.getEpics().toString() + '\n' +
                taskManager.getSubTasks().toString() + '\n');


        System.out.println("c. Получение по идентификатору.");
        System.out.println("Вывод на экран. Задача, id 1 - " + taskManager.taskForId(1));
        System.out.println("Вывод на экран. эпик, id 5 - " + taskManager.epicForId(5));
        System.out.println("Вывод на экран. Подзадача, id 8 - " + taskManager.subTaskForId(8));


        System.out.println("Получение списка всех подзадач определённого эпика.");
        System.out.println("Список подзадач Epic'a №5:" + taskManager.tasksOfEpic(5));



        System.out.println("Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра." +
                '\n' + "task4");
        Task task4 = new Task("Задача 1.2", "новое описание задачи", Status.DONE);
        taskManager.updateTask(task4);


        System.out.println("обновление статуса SubTask и Epic" +  '\'' + "SubTask11 , SubTask12");
        SubTask8 = new SubTask("Подзадача 8.2", "новое описание задачи",
                Status.IN_PROGRESS, 5);
        taskManager.updateSubTask(SubTask8);
        SubTask10 = new SubTask("Подзадача 10.2", "новое описание задачи",
                Status.IN_PROGRESS, 7);
        taskManager.updateSubTask(SubTask10);

        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\n' +
                taskManager.getEpics().toString() + '\n' +
                taskManager.getSubTasks().toString() + '\n');


        System.out.println("a. Получение списка всех задач.");
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');


        System.out.println("Удаление по идентификатору." +  '\n' + "Task(2) , Epic(6) , SubTask(9)");
        taskManager.deleteTask(2);
        taskManager.deleteEpic(6);
        taskManager.deleteSubTask(9);
        System.out.println(taskManager.getTasks().toString() + '\n' +
                taskManager.getEpics().toString() + '\n' +
                taskManager.getSubTasks().toString() + '\n');

        System.out.println("a. Получение списка всех задач.");
        System.out.println("Список всех задач:");
        System.out.println(taskManager.getTasks().toString() + '\'' +
                taskManager.getEpics().toString() + '\'' +
                taskManager.getSubTasks().toString() + '\'');

    }
}