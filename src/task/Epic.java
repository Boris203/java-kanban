package task;

import java.util.ArrayList;

public class Epic extends Task {
    protected ArrayList<Integer> SubTasksIdForEpic = new ArrayList<>();

    public Epic(String nameToTask, String descriptionToTask, Status statusToTask) {
        super(nameToTask, descriptionToTask, statusToTask);
    }


    public ArrayList<Integer> getSubTasksId() {
        return SubTasksIdForEpic;
    }

    public void addSubTasksId(Integer idSubTask) {
        SubTasksIdForEpic.add(idSubTask);
    }

    public void removeSubTaskIdToEpics (int SubTaskId) {
        SubTasksIdForEpic.remove(SubTaskId);
    }

    public void clearSubTaskIdToEpics () {
        SubTasksIdForEpic.clear();
    }
}