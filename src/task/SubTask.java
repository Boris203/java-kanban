package task;

import taskManager.Status;

import java.util.ArrayList;

public class SubTask extends Task {
    private Integer epicsIdForSubTask;

    public SubTask(String nameToTask, Status statusToTask, Integer epicsIdForSudTask) {
        super(nameToTask, statusToTask);
        this.epicsIdForSubTask = epicsIdForSudTask;
    }

    public Integer getEpicsIdForSubTask() {
        return epicsIdForSubTask;
    }

    public void setEpicsIdForSubTask(Integer epicsIdForSubTask) {
        this.epicsIdForSubTask = epicsIdForSubTask;
    }

    public void removeSubTaskIdToEpics (int SubTaskId) {
        epicsIdForSubTask = null;
    }
}