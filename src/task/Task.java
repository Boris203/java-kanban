package task;

import java.util.Objects;

public class Task {

    protected String nameToTask;
    protected String descriptionToTask;
    protected Status statusToTask;
    protected Integer idToTask;

    public Task(String nameToTask, String descriptionToTask, Status statusToTask) {
        this.nameToTask = nameToTask;
        this.descriptionToTask = descriptionToTask;
        this.statusToTask = statusToTask;
    }

    public String getNameToTask() {
        return nameToTask;
    }

    public void setNameToTask(String nameToTask) {
        this.nameToTask = nameToTask;
    }

    public Status getStatusToTask() {
        return statusToTask;
    }

    public void setStatusToTask(Status statusToTask) {
        this.statusToTask = statusToTask;
    }

    public String getDescriptionToTask() {
        return descriptionToTask;
    }

    public void setDescriptionToTask(String descriptionToTask) {
        this.descriptionToTask = descriptionToTask;
    }

    public Integer getTaskId() {
        return idToTask;
    }

    public void setTaskId(Integer taskId) {
        this.idToTask= taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(nameToTask, task.nameToTask) &&
               Objects.equals(descriptionToTask, task.descriptionToTask) &&
               statusToTask == task.statusToTask &&
               Objects.equals(idToTask, task.idToTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameToTask, descriptionToTask, statusToTask, idToTask);
    }

    @Override
    public String toString() {
        return "Task{" + '\n' +
                "nameToTask='" + nameToTask + '\n' +
                ", descriptionToTask='" + descriptionToTask + '\n' +
                ", statusToTask=" + statusToTask + '\n' +
                ", idToTask=" + idToTask + '\n' +
                '}';
    }
}