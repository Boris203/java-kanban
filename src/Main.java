import taskManager.TaskManager;
import taskManager.Status;
import task.Task;
import task.Epic;
import task.Subtask;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача 1", "Описание 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", Status.IN_PROGRESS);
        Task task3 = new Task("Задача 3", "Описание 3", Status.DONE);
        Epic epic1 = new Epic("Epic 1", "Описание 1");
        Epic epic2 = new Epic("Epic 1", "Описание 2");
        Epic epic3 = new Epic("Epic 3", "Описание 3");
        final int idTask1 = taskManager.addNewTask(task1);
        final int idTask2 = taskManager.addNewTask(task2);
        final int idTask3 = taskManager.addNewTask(task3);
        final int idEpic1 = taskManager.addNewEpic(epic1);
        final int idEpic2 = taskManager.addNewEpic(epic2);
        final int idEpic3 = taskManager.addNewEpic(epic3);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", Status.NEW, idEpic1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", Status.NEW, idEpic1);
        Subtask subtask3 = new Subtask("Подзадача 3", "Описание 3", Status.DONE, idEpic3);
        final int idSubtask1 = taskManager.addNewSubtask(subtask1);
        final int idSubtask2 = taskManager.addNewSubtask(subtask2);
        final int idSubtask3 = taskManager.addNewSubtask(subtask3);


        //1. Вывод всех Task.
        taskManager.printAllTasks();

        //2. Печать задачи, подзадачи и эпика.
        System.out.println("Все задачи: \n" + taskManager.getTasks());
        System.out.println("Все Epic: \n" + taskManager.getEpics());
        System.out.println("Все подзадачи: \n" + taskManager.getSubtasks());
        System.out.println("Поиск задачи по ID: \n" + taskManager.findTaskId(3));
        System.out.println("Поиск задачи по ID: \n" + taskManager.findTaskId(1));
        System.out.println("Поиск Epic по ID: \n" + taskManager.findEpicId(4));
        System.out.println("Поиск Epic по ID: \n" + taskManager.findEpicId(1));
        System.out.println("Поиск подзадачи по ID: \n" + taskManager.findSubtaskId(4));
        System.out.println("Поиск подзадачи по ID: \n" + taskManager.findSubtaskId(7));


        //3. получение всех подзадач определенного эпика
        System.out.println("Список задач Epic:" + taskManager.findSubtasksEpic(epic1));


        //4. обновление task
        System.out.println("До обновления:");
        taskManager.printAllTasks();
        Task task4 = new Task(1, "Задача 1", "Описание 2", Status.DONE);
        taskManager.updateTask(task4);
        System.out.println("После обновления:");
        taskManager.printAllTasks();

        //5. обновление статуса Subtask и Epic
        taskManager.updateSubtask(new Subtask(7, "Подзадача 7", "Описание 7", Status.IN_PROGRESS, 4));
        taskManager.updateSubtask(new Subtask(8, "Подзадача 8", "Описание 8", Status.IN_PROGRESS, 4));
        taskManager.updateSubtask(new Subtask(9, "Подзадача 9", "Описание 9", Status.NEW, 6));

        //6. удаление данных
        taskManager.printAllTasks();
        taskManager.deleteTaskId(1); //удаление task по id
        taskManager.printAllTasks(); //проверка вывода
        taskManager.deleteAllTasks(); //удаление всех task
        taskManager.printAllTasks(); //проверка вывода

        taskManager.deleteSubtaskId(7); //удаление Subtask по id и обновление статуса Epic
        taskManager.printAllTasks();
        taskManager.deleteAllSubtasks(); //удаление всех Subtask
        taskManager.printAllTasks();

        taskManager.deleteEpicId(4); //удаление эпика по id
        taskManager.printAllTasks();

        taskManager.deleteAllEpics(); //удаление всех Epic
        taskManager.printAllTasks();
    }
}