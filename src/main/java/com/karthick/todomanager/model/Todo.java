package com.karthick.todomanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String todo_text;
    private Date due_date;
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

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todo_text='" + todo_text + '\'' +
                ", due_date=" + due_date +
                ", completed=" + completed +
                '}';
    }
}
