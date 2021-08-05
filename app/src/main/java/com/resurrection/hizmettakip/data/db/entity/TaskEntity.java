package com.resurrection.hizmettakip.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity(tableName = "task")
public class TaskEntity {

    @PrimaryKey
    private long id;

    private String task;

    private String date;

    private String staff;


    public TaskEntity(long id, String task, String date, String staff) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.staff = staff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
