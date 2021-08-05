package com.resurrection.hizmettakip.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;
import com.resurrection.hizmettakip.data.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository taskRepository;
    private TaskRepository taskRepository1;
    private LiveData<List<TaskEntity>> data;
    private LiveData<List<StaffEntity>> staffData;
    private LiveData<List<StaffEntity>> oneStaffData;


    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskRepository1 = new TaskRepository(application);
        staffData = taskRepository1.getAllStaff();
        taskRepository = new TaskRepository(application);
        data = taskRepository.getAllTask();
        oneStaffData = taskRepository.getStaff();
    }

    public void insert(TaskEntity taskEntity)
    {
        taskRepository.insert(taskEntity);
    }
    public void update(TaskEntity taskEntity)
    {
        taskRepository.update(taskEntity);
    }
    public void delete(TaskEntity taskEntity)
    {
        taskRepository.delete(taskEntity);
    }
    public LiveData<List<TaskEntity>> getAllTask()
    {
        return data;
    }

    public void insertStaff(StaffEntity staffEntity){
        taskRepository.insertStaff(staffEntity);
    }
    public void updateStaff(StaffEntity staffEntity){ taskRepository.updateStaff(staffEntity); }
    public void deleteStaff(StaffEntity staffEntity){ taskRepository.deleteStaff(staffEntity); }
    public LiveData<List<StaffEntity>> getAllStaff(){
        return staffData;
    }
    public LiveData<List<StaffEntity>> getStaff(){
        return oneStaffData;
    }





}
