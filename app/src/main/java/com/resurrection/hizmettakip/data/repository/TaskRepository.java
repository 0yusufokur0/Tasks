package com.resurrection.hizmettakip.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.resurrection.hizmettakip.data.db.TaskDatabase;
import com.resurrection.hizmettakip.data.db.dao.TaskDao;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<TaskEntity>> data;
    private LiveData<List<StaffEntity>> staffData;
    private LiveData<List<StaffEntity>> oneStaffData;

    public TaskRepository(Application application) {
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
        data = taskDao.getAllTask();
        staffData = taskDao.getAllStaff();
        oneStaffData = taskDao.getStaff(null);

    }

    public void insert(TaskEntity taskEntity) {
        new InsertTaskAsyncTask(taskDao).execute(taskEntity);
    }
    public void update(TaskEntity taskEntity) {
        new UpdateTaskAsyncTask(taskDao).execute(taskEntity);
    }
    public void delete(TaskEntity taskEntity) {
        new DeleteTaskAsyncTask(taskDao).execute(taskEntity);
    }
    public LiveData<List<TaskEntity>> getAllTask() {
        return data;
    }

    public void insertStaff(StaffEntity staffEntity) {
        new InsertStaffAsyncTask(taskDao).execute(staffEntity);
    }
    public void updateStaff(StaffEntity staffEntity) {
        new UpdateStaffAsyncTask(taskDao).execute(staffEntity);
    }
    public void deleteStaff(StaffEntity staffEntity) {
        new DeleteStaffAsyncTask(taskDao).execute(staffEntity);
    }
    public LiveData<List<StaffEntity>> getAllStaff() {
        return staffData;
    }
    public LiveData<List<StaffEntity>> getStaff() {
        return oneStaffData;
    }

    public void test(StaffEntity staffEntity){
        class testClass extends AsyncTask<StaffEntity,Void,Void>{
            TaskDao taskDao;

            public testClass(TaskDao taskDao) {
                this.taskDao = taskDao;
            }

            @Override
            protected Void doInBackground(StaffEntity... staffEntities) {
                staffEntities[0] = staffEntity;
                taskDao.InsertStaff(staffEntities[0]);

                return null;
            }
        }
    }








    // Crud Staff Async Class
    private class InsertStaffAsyncTask extends AsyncTask<StaffEntity, Void, Void> {
        TaskDao taskDao;

        public InsertStaffAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }


        @Override
        protected Void doInBackground(StaffEntity... staffEntities) {
            taskDao.InsertStaff(staffEntities[0]);
            return null;
        }
    }










    private class UpdateStaffAsyncTask extends AsyncTask<StaffEntity, Void, Void> {
        TaskDao taskDao;

        public UpdateStaffAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }


        @Override
        protected Void doInBackground(StaffEntity... staffEntities) {
            taskDao.UpdateStaff(staffEntities[0]);
            return null;
        }
    }
    private class DeleteStaffAsyncTask extends AsyncTask<StaffEntity, Void, Void> {
        TaskDao taskDao;

        public DeleteStaffAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }


        @Override
        protected Void doInBackground(StaffEntity... staffEntities) {
            taskDao.DeleteStaff(staffEntities[0]);
            return null;
        }
    }

    // Crud Task Async Class
    private static class InsertTaskAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskEntity... taskEntities) {
            taskDao.Insert(taskEntities[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskEntity... taskEntities) {
            taskDao.Update(taskEntities[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskEntity... taskEntities) {
            taskDao.Delete(taskEntities[0]);
            return null;
        }
    }




}
























