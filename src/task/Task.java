package task;
import taskManager.Status;

import java.util.Objects;

public class Task {

    protected static Integer  idToTask = 1;
    protected String nameToTask;
    protected Status statusToTask;


    public Task(String nameToTask, Status statusToTask) {
        this.nameToTask = nameToTask;
        this.statusToTask = statusToTask;
        idToTask++;
    }


    public Integer getIdToTask() {
        return idToTask;
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

    @Override
    public int hashCode() {
        int result = Objects.hash(idToTask, nameToTask, statusToTask);
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "idToTask=" + idToTask + '\'' +
                ", nameToTask='" + nameToTask + '\'' +
                ", statusToTask=" + statusToTask + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(nameToTask, task.nameToTask) && statusToTask == task.statusToTask;
    }
}