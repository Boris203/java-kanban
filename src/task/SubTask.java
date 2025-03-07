package task;

public class SubTask extends Task {
    private Integer epicsIdForSubTask;

    public SubTask(String nameToTask, String descriptionToTask, Status statusToTask, Integer epicsIdForSubTask) {
        super(nameToTask, descriptionToTask, statusToTask);
        this.epicsIdForSubTask = epicsIdForSubTask;
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