package com.resurrection.hizmettakip.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.resurrection.hizmettakip.data.db.dao.TaskDao;
import com.resurrection.hizmettakip.data.db.entity.Converters;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;

@Database(entities = {TaskEntity.class, StaffEntity.class, },version = 1)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;
    public abstract TaskDao taskDao();


    public static synchronized TaskDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"tasks")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    public static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull  SupportSQLiteDatabase db) {
            new PopulateDbAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private TaskDao taskDao;

        public PopulateDbAsyncTask(TaskDatabase taskDatabase) {
            taskDao = taskDatabase.taskDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

/*
            taskDao.Insert(new TaskEntity(1,"first task","asd","dfgdfg"));
*/

            return null;
        }
    }


    }


















