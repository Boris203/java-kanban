package manager;

import java.util.ArrayList;
import java.util.HashMap;

import task.Status;
import task.Task;
import task.Epic;
import task.SubTask;

public class TaskManager {
    private HashMap<Integer, Task> idToTask = new HashMap<>();
    private HashMap<Integer, Epic> idToEpics = new HashMap<>();
    private HashMap<Integer, SubTask> idToSubTask = new HashMap<>();
    private Integer  taskId = 1;


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

    public void removeAllEpics() {
        idToEpics.clear();
        idToSubTask.clear();
    }

    public void removeAllSubTask() {
        ArrayList<Integer> epicIdForSubTasks = new ArrayList<>();
        for (SubTask subTask : idToSubTask.values()) {
            epicIdForSubTasks.add(subTask.getEpicsIdForSubTask());
        }
        for (Integer epicIdForSubTask : epicIdForSubTasks) {
            idToEpics.get(epicIdForSubTask).clearSubTaskIdToEpics();
            Epic epic = idToEpics.get(epicIdForSubTask);
            epic.setStatusToTask(Status.NEW);
        }
    }

    //c. Получение по идентификатору.
    public Task taskForId(int id) {
            return idToTask.get(id);
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
        Integer id = taskId;
        idToTask.put(id, task);
        taskId++;
    }

    public void createEpic(Epic epic) {
        Integer id = taskId;
        idToEpics.put(id, epic);
        taskId++;
    }

    public void createSubTask(SubTask subTask) {
        Integer id = taskId;
        idToSubTask.put(id, subTask);
        Integer epicIdForSubtask = subTask.getEpicsIdForSubTask();
        Epic epicIdForST =  idToEpics.get(epicIdForSubtask);
        epicIdForST.addSubTasksId(id);
        epicIdForST.toString();
        taskId++;
    }

    //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Task task) {
        for (Integer iter = 0; iter < idToTask.size(); iter++) {
            if (task.equals(idToTask.get(iter))) {
                idToTask.put(iter, task);
            }
        }
    }

    public void updateEpic(Epic epic) {
        for (Integer iter = 0; iter < idToEpics.size(); iter++) {
            if (epic.equals(idToEpics.get(iter))) {
                idToEpics.put(iter, epic);
            }
        }
    }

    public void updateSubTask(SubTask subTask) {
        for (Integer iter = 0; iter < idToSubTask.size(); iter++) {
            if (subTask.equals(idToSubTask.get(iter))) {
                idToSubTask.put(iter, subTask);
                Integer epicIdForSubTask = idToSubTask.get(iter).getEpicsIdForSubTask();
                updateEpicStatus(epicIdForSubTask);
            }

        }
    }

    //f. Удаление по идентификатору
    public void deleteTask(Integer id) {
            idToTask.remove(id);
        }


    public void deleteEpic(Integer id) {
        for (Integer idSubTask : idToEpics.get(id).getSubTasksId()) {
            idToSubTask.remove(idSubTask);
        }
        idToEpics.get(id).clearSubTaskIdToEpics();
        idToEpics.remove(id);
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
        }
        return subTask;
    }

    //Управление статусами Эпика
    private void updateEpicStatus(Integer EpicId) {
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