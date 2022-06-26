package com.karthick.todomanager.dto;

public class TodoDto {
    private int id;
    private String todo_text;
    private String due_date;
    private boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo_text() {
        return todo_text;
    }

    public void setTodo_text(String todo_text) {
        this.todo_text = todo_text;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
