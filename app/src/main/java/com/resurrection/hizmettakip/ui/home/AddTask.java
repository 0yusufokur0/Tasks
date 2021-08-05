package com.resurrection.hizmettakip.ui.home;

import static com.resurrection.hizmettakip.data.Constants.getDate;
import static com.resurrection.hizmettakip.data.Constants.idCreater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.resurrection.hizmettakip.R;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;
import com.resurrection.hizmettakip.ui.base.TaskViewModel;
import com.resurrection.hizmettakip.util.StaffAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {


    EditText taskTxt;
/*
    EditText description;
*/
    Button cancel;
    Button save;
    private StaffAdapter staffAdapter = new StaffAdapter();
    private TaskViewModel taskViewModel;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        init();
        getAndSetItems();
        getSupportActionBar().setTitle("Add Task");



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "kaydedilmedi", Toast.LENGTH_LONG).show();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
        staffAdapter.setOnItemClickListener(new StaffAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StaffEntity staffEntity) {

            }
        });
        staffAdapter.setOnItemLongClickListener(new StaffAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(StaffEntity staffEntity) {

            }
        });


    }
    private void init(){
        taskTxt = findViewById(R.id.taskTxt);
        recyclerView = findViewById(R.id.addTaskStaffRecyclerview);
        cancel = findViewById(R.id.buttonCancel);
        save = findViewById(R.id.buttonSave);
    }
    public void saveTask()
    { /*
    create task id
    create task name
    create task date

    get staff id
    get staff name
    get staff surname

    create assign id
    get task id
    get staff id

     */

        long taskId = idCreater();
        long assignId = idCreater();

        taskViewModel.insert(new TaskEntity(idCreater(),taskTxt.getText().toString(),getDate(),staffAdapter.selectedItems.toString()));
        finish();

/*        String TodoTitle = "asdasd";
        String TodoDescription = "description";
        Intent i = new Intent();
        i.putExtra("todoTitle", TodoTitle);
        i.putExtra("todoDescription", TodoDescription);
        setResult(RESULT_OK, i);
        finish();*/

    }
    private void getAndSetItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(staffAdapter);
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        taskViewModel.getAllStaff().observe(this, new Observer<List<StaffEntity>>() {
            @Override
            public void onChanged(List<StaffEntity> staffEntities) {
                staffAdapter.setStaff(staffEntities);
            }
        });

    }



}

































