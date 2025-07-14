package com.example.Todolist.model;

public class Todomodel {

    private int taskno;
    private String taskname;
    private String description;

    public Todomodel() {
    }

    public Todomodel(int taskno, String taskname, String description) {
        this.taskno = taskno;
        this.taskname = taskname;
        this.description = description;
    }

    public int getTaskno() {
        return taskno;
    }

    public void setTaskno(int taskno) {
        this.taskno = taskno;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
