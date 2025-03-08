package task;

import java.util.ArrayList;

public class Epic extends Task {
    protected ArrayList<Integer> subTasksIdForEpic = new ArrayList<>();

    public Epic(String nameToTask, String descriptionToTask, Status statusToTask) {
        super(nameToTask, descriptionToTask, statusToTask);
    }


    public ArrayList<Integer> getSubTasksId() {
        return subTasksIdForEpic;
    }

    public void addSubTasksId(Integer idSubTask) {
        subTasksIdForEpic.add(idSubTask);
    }

    public void removeSubTaskIdToEpics (int SubTaskId) {
        subTasksIdForEpic.remove(SubTaskId);
    }

    public void clearSubTaskIdToEpics () {
        subTasksIdForEpic.clear();
    }
}