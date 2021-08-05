package com.resurrection.hizmettakip.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.resurrection.hizmettakip.R;
import com.resurrection.hizmettakip.data.Constants;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;
import com.resurrection.hizmettakip.ui.base.TaskViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static com.resurrection.hizmettakip.data.Constants.idCreater;

public class UpdateTask extends AppCompatActivity {
    EditText title;
    EditText description;
    Button cancel;
    Button save;
    long taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        getSupportActionBar().setTitle("Update Task");




        title = findViewById(R.id.editTextTitleUpdate);
        description = findViewById(R.id.editTextDescriptionUpdate);
        cancel = findViewById(R.id.buttonCancelUpdate);
        save = findViewById(R.id.buttonSaveUpdate);






        getData();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nothing Updated", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titlelast = title.getText().toString();
                String descriptionlast = description.getText().toString();

     /*           if (taskId != -1) {
                   saveTask(new TaskEntity(idCreater(),titlelast,descriptionlast,getTime(),2));
                }*/
            }
        });
    }





    public void getData() {
        Intent i = getIntent();
        taskId = i.getLongExtra("id", -1);
        String taskTitle = i.getStringExtra("title");
        String taskDescription = i.getStringExtra("description");

        title.setText(taskTitle);
        description.setText(taskDescription);
    }

    public void saveTask(TaskEntity taskEntity) {
        TaskViewModel taskViewModel;

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTask().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                //update Recyclerview


            }
        });
        taskViewModel.insert(taskEntity);
    }





}
