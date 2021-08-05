package com.resurrection.hizmettakip.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void Insert(TaskEntity taskEntity);

    @Update
    void Update(TaskEntity taskEntity);

    @Delete
    void Delete(TaskEntity taskEntity);


    @Query("SELECT * FROM task ORDER BY id ASC")
    LiveData<List<TaskEntity>> getAllTask();


    @Insert
    void InsertStaff(StaffEntity staffEntity);

    @Update
    void UpdateStaff(StaffEntity staffEntity);

    @Delete
    void DeleteStaff(StaffEntity staffEntity);


    @Query("SELECT * FROM staff ORDER BY id ASC")
    LiveData<List<StaffEntity>> getAllStaff();

    @Query("SELECT * FROM staff WHERE id LIKE :asd ")
    LiveData<List<StaffEntity>> getStaff(Long asd);



}











