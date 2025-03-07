package task;

import java.util.Objects;

public class Task {

    // protected static Integer  idToTask = 1;
    protected String nameToTask;
    protected String descriptionToTask;
    protected Status statusToTask;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(nameToTask, task.nameToTask) &&
               Objects.equals(descriptionToTask, task.descriptionToTask) &&
               statusToTask == task.statusToTask;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(nameToTask, descriptionToTask, statusToTask);
    }

    @Override
    public String toString() {
        return "Task{" +
                "nameToTask='" + nameToTask + '\n' +
                ", descriptionToTask='" + descriptionToTask + '\n' +
                ", statusToTask=" + statusToTask + '\n' +
                '}';
    }
}