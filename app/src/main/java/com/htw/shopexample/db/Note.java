package com.htw.shopexample.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.htw.shopexample.util.TimestampConverter;

import java.util.Date;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String desc;

    private int priority;

    private boolean markedTask;

    public void setMarkedTask(boolean markedTask) {
        this.markedTask = markedTask;
    }

    public boolean isMarkedTask() {
        return markedTask;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @ColumnInfo(name = "created_date")
    @TypeConverters({TimestampConverter.class})
    private Date createDate;

    public Note(String title, String desc, int priority, Date createDate, boolean markedTask) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
        this.createDate = createDate;
        this.markedTask = markedTask;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}


