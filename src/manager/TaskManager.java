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
    }

    public void removeAllEpics() {
        idToEpics.clear();
        idToSubTask.clear();
    }

    public void removeAllSubTask() {
        idToSubTask.clear();
        for(Epic epic : idToEpics.values()) {
            epic.clearSubTaskIdToEpics();
        }
    }

    //c. Получение по идентификатору.
    public Task taskForId(int id) {
            return idToTask.get(id);
    }

    public Epic epicForId(int id) {
        return idToEpics.get(id);
    }

    public SubTask subTaskForId(int id) {
            return idToSubTask.get(id);
    }

    //d. Создание. Сам объект должен передаваться в качестве параметра.
    public void createTask(Task task) {
        Integer id = taskId;
        idToTask.put(id, task);
        task.setTaskId(id);
        taskId++;
    }

    public void createEpic(Epic epic) {
        Integer id = taskId;
        idToEpics.put(id, epic);
        epic.setTaskId(id);
        taskId++;
    }

    public void createSubTask(SubTask subTask) {

        Epic epic = idToEpics.get(subTask.getEpicsIdForSubTask());
        if (epic == null) {
            return;
        }
        Integer id = taskId;
        idToSubTask.put(id, subTask);
        subTask.setTaskId(id);
        Integer epicIdForSubtask = subTask.getEpicsIdForSubTask();
        Integer epicId =  idToSubTask.get(id).getEpicsIdForSubTask();
        Epic epicIdForST =  idToEpics.get(epicIdForSubtask);
        epicIdForST.addSubTasksId(id);
        updateEpicStatus(epicId);
        taskId++;
    }

    //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Task task) {
        Integer taskId = task.getTaskId();
        if (idToTask.containsKey(taskId)) {
            idToTask.put(taskId, task);
        }
    }

    public void updateEpic(Epic epic) {
        Integer epicId = epic.getTaskId();
        if (idToTask.containsKey(epicId)) {
            idToTask.put(epicId, epic);
        }
    }

    public void updateSubTask(SubTask subTask) {
        Integer subTaskId = subTask.getTaskId();
        if (idToTask.containsKey(subTaskId)) {
            idToTask.put(subTaskId, subTask);
            updateEpicStatus(subTask.getEpicsIdForSubTask());
        }
    }

    //f. Удаление по идентификатору
    public void deleteTask(Integer id) {
            idToTask.remove(id);
        }


    public void deleteEpic(Integer id) {
        Epic epic = idToEpics.get(id);
        if (epic == null) {
            return;
        }
        for (Integer idSubTask : idToEpics.get(id).getSubTasksId()) {
            idToSubTask.remove(idSubTask);
        }
        idToEpics.remove(id);
    }

    public void deleteSubTask(Integer id) {
        Integer epicId =  idToSubTask.get(id).getEpicsIdForSubTask();
        if (idToEpics.containsKey(id)) {
            idToSubTask.remove(id);
            idToEpics.get(epicId).removeSubTaskIdToEpics(id);
        }
        updateEpicStatus(epicId);
    }


    // Получение списка всех подзадач определённого эпика.
    public ArrayList<SubTask> tasksOfEpic(Integer idOfEpic) {
        ArrayList<SubTask> subTask = new ArrayList<>();
        Epic epic = idToEpics.get(idOfEpic);
        if (epic == null) {
            return subTask;
        }
        ArrayList<Integer> subTasksOfEpics = idToEpics.get(idOfEpic).getSubTasksId();
        for (Integer subTasks : subTasksOfEpics) {
            subTask.add(idToSubTask.get(subTasks));
        }
        return subTask;
    }

    //Управление статусами Эпика
    private void updateEpicStatus(Integer epicId) {
        boolean allDone = true;
        boolean allNew = true;
        ArrayList<Integer> subTasksOfEpics = idToEpics.get(epicId).getSubTasksId();
        ArrayList<Status> statusSubTasks = new ArrayList<>();
        for (Integer SubTask : subTasksOfEpics) {
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
            idToEpics.get(epicId).setStatusToTask(Status.DONE);
        } else if (allNew) {
            idToEpics.get(epicId).setStatusToTask(Status.NEW);
        } else {
            idToEpics.get(epicId).setStatusToTask(Status.IN_PROGRESS);
        }
    }

}