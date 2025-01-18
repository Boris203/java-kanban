package taskManager;

import java.util.ArrayList;
import java.util.HashMap;
import task.Task;
import task.Epic;
import task.Subtask;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subTasks = new HashMap<>();
    private static int taskID = 0;


    public int addNewTask(Task task) {
        taskID++;
        task.setId(taskID);
        tasks.put(task.getId(), task);
        System.out.println("Task added");
        return taskID;
    }

    public int addNewEpic(Epic epic) {
        taskID++;
        epic.setId(taskID);
        epics.put(epic.getId(), epic);
        System.out.println("Epic добавлен");
        return taskID;
    }

    public int addNewSubtask(Subtask subtask) {
        taskID++;
        subtask.setId(taskID);
        subTasks.put(subtask.getId(), subtask);
        System.out.println("Subtask добавлен");

        Epic epic = epics.get(subtask.epicId);
        epic.addSubtaskIdToEpics(taskID);
        updateEpicStatus(epic);
        return taskID;
    }


    public void printAllTasks() {
        System.out.println("Вывод всех задач!");
        System.out.println(tasks.toString());
        System.out.println(epics.toString());
        System.out.println(subTasks.toString());
    }


    public void deleteAllTasks() {
        tasks.clear();
        System.out.println("Все задачи удалены!");
    }

    public void deleteTaskId(int id) {
        tasks.remove(id);
        System.out.println("Задача по ID " + id + " удалена!");
    }

    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
        System.out.println("Все Epic и Subtask удалены!");
    }

    public void deleteEpicId(int id) {
        ArrayList<Integer> idSubtasks = epics.get(id).getIdSubtaskEpics();
        for (int idSubtask : idSubtasks) {
            subTasks.remove(idSubtask);
        }
        epics.remove(id);

        System.out.println("Эпик и его подзадачи по ID " + id + " удалены.");
    }

    public void deleteAllSubtasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.setStatus(Status.NEW);
        }
        System.out.println("Все Subtask удалены! Присвоен статус всем Epic - \"New\"");
    }

    public void deleteSubtaskId(int id) {
        int epicId = subTasks.get(id).epicId;
        updateEpicStatus(epics.get(epicId));
        subTasks.remove(id);
        System.out.println("Subtask удален, статус Epic обновлен на : " + epics.get(epicId));
    }


    public ArrayList<Task> getTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<Subtask>(subTasks.values());
    }

    public Task findTaskId(int id) {
        return tasks.get(id);
    }

    public Epic findEpicId(int id) {
        return epics.get(id);
    }

    public Subtask findSubtaskId(int id) {
        return subTasks.get(id);
    }


    public ArrayList<Integer> findSubtasksEpic(Epic epic) {
        return new ArrayList<>(epic.getIdSubtaskEpics());
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
        System.out.println(tasks);
    }

    public void updateSubtask(Subtask subtask) {
        subTasks.put(subtask.getId(), subtask);
        updateEpicStatus(epics.get(subtask.epicId));
    }

    public void updateEpicStatus(Epic epic) {
        boolean allDone = true;
        boolean allNew = true;

        ArrayList<Integer> idSubtasksEpics = epic.getIdSubtaskEpics();
        System.out.println(idSubtasksEpics);
        for (Integer i : idSubtasksEpics) {
            if (subTasks.get(i).getStatus() != Status.DONE) {
                allDone = false;
            }
            if (subTasks.get(i).getStatus() != Status.NEW) {
                allNew = false;
            }
            if (!allDone && !allNew) {
                break;
            }
        }
        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (allNew) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
        System.out.println("Статус эпика обновлен: " + epic.getStatus());

    }
}