package com.ioserv.done;

public class Task {
    int Image;
    String DueDate;
    String Reward;
    String TaskFrecuency;

    public Task(int image, String dueDate, String reward, String taskFrecuency) {
        Image = image;
        DueDate = dueDate;
        Reward = reward;
        TaskFrecuency = taskFrecuency;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getReward() {
        return Reward;
    }

    public void setReward(String reward) {
        Reward = reward;
    }

    public String getTaskFrecuency() {
        return TaskFrecuency;
    }

    public void setTaskFrecuency(String taskFrecuency) {
        TaskFrecuency = taskFrecuency;
    }
}
