package taskManager;

import java.util.ArrayList;
import java.util.HashMap;
import task.Task;
import task.Epic;
import task.SubTask;

public class TaskManager {
    private HashMap<Integer, Task> idToTask = new HashMap<>();
    private HashMap<Integer, Epic> idToEpics = new HashMap<>();
    private HashMap<Integer, SubTask> idToSubTask = new HashMap<>();


    // проверка наличия элемента
    public boolean existsInTasks(int id) {
        return idToTask.containsKey(id);
    }

    public boolean existsInEpics(int id) {
        return idToEpics.containsKey(id);
    }

    public boolean existsInSubTasks(int id) {
        return idToSubTask.containsKey(id);
    }


    //        a. Получение списка всех задач.
    public ArrayList<Task> getTasks() {
        return new ArrayList<Task>(idToTask.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<Epic>(idToEpics.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<SubTask>(idToSubTask.values());
    }


    //b. Удаление всех задач.
    public void removeAllTask() {
        idToTask.clear();
        idToEpics.clear();
        idToSubTask.clear();
    }

    //c. Получение по идентификатору.
    public Task taskForId(int id) {
        if (existsInTasks(id)) {
            return idToTask.get(id);
        } else {
            return null;
        }
    }

    public Epic epicForId(int id) {
        if (existsInEpics(id)) {
            return idToEpics.get(id);
        } else {
            return null;
        }
    }

    public SubTask SubTaskForId(int id) {
        if (existsInSubTasks(id)) {
            return idToSubTask.get(id);
        } else {
            return null;
        }
    }

    //d. Создание. Сам объект должен передаваться в качестве параметра.
    public void createTask(Task task) {
        Integer id = task.getIdToTask();
        idToTask.put(id, task);
    }

    public void createEpic(Epic epic) {
        Integer id = epic.getIdToTask();
        idToEpics.put(id, epic);
    }

    public void createSubTask(SubTask SubTask) {
        Integer id = SubTask.getIdToTask();
        idToSubTask.put(id, SubTask);
    }

    //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Integer id, Task task) {
        if (existsInTasks(id)) {
            idToTask.put(id, task);
        }
    }

    public void updateEpic(Integer id, Epic epic) {
        if (existsInEpics(id)) {
            idToEpics.put(id, epic);
            updateEpicStatus(id);
        }
    }

    public void updateSubTask(Integer id, SubTask SubTask) {
        if (existsInSubTasks(id)) {
            idToSubTask.put(id, SubTask);
            Integer epicIdForSubTask = idToSubTask.get(id).getEpicsIdForSubTask();
            updateEpicStatus(epicIdForSubTask);
        }
    }

    //f. Удаление по идентификатору
    public void deleteTask(Integer id) {
        if (existsInTasks(id)) {
            idToTask.remove(id);
        }
    }

    public void deleteEpic(Integer id) {
        if (existsInEpics(id)) {
            for (Integer idSubTask : idToEpics.get(id).getSubTasksId()) {
                idToSubTask.remove(idSubTask);
            }
            idToEpics.get(id).clearSubTaskIdToEpics();
            idToEpics.remove(id);
        }
    }

    public void deleteSubTask(Integer id) {
        if (existsInSubTasks(id)) {
            idToSubTask.remove(id);
        }
    }


    // Получение списка всех подзадач определённого эпика.
    public ArrayList<SubTask> tasksOfEpic(Integer idOfEpic) {
        ArrayList<SubTask> subTask = new ArrayList<>();
        if (existsInEpics(idOfEpic)) {
            ArrayList<Integer> SubTasksOfEpics = idToEpics.get(idOfEpic).getSubTasksId();
            for (Integer subTasks : SubTasksOfEpics) {
                subTask.add(idToSubTask.get(subTasks));
            }
            return subTask;
        }
        return null;
    }

    //Управление статусами Эпика
    public void updateEpicStatus(Integer EpicId) {
        boolean allDone = false;
        boolean allNew = false;
        ArrayList<Integer> SubTasksOfEpics = idToEpics.get(EpicId).getSubTasksId();
        ArrayList<Status> statusSubTasks = new ArrayList<>();
        for (Integer SubTask : SubTasksOfEpics) {
            Status statusST = idToSubTask.get(SubTask).getStatusToTask();
            statusSubTasks.add(statusST);
        }
        for (Status statusSubTask : statusSubTasks) {
            if (statusSubTask != Status.DONE) {
                allDone = false;
                break;
            }
        }
        for (Status statusSubTask : statusSubTasks) {
            if (statusSubTask != Status.NEW) {
                allNew = false;
                break;
            }
        }

        if (allDone) {
            idToEpics.get(EpicId).setStatusToTask(Status.DONE);
        } else if (allNew) {
            idToEpics.get(EpicId).setStatusToTask(Status.NEW);
        } else {
            idToEpics.get(EpicId).setStatusToTask(Status.IN_PROGRESS);
        }
    }

}