package com.resurrection.hizmettakip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;
import com.resurrection.hizmettakip.ui.base.TaskViewModel;
import com.resurrection.hizmettakip.ui.home.ManageStaff;
import com.resurrection.hizmettakip.ui.home.AddTask;
import com.resurrection.hizmettakip.ui.home.UpdateTask;
import com.resurrection.hizmettakip.util.StaffAdapter;
import com.resurrection.hizmettakip.util.TaskAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter = new TaskAdapter();
    private RecyclerView recyclerView;
    private StaffAdapter staffAdapter = new StaffAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getAndSetItems();
        itemGestures();
        actionBar("Hizmetler");

/*
        taskViewModel.insertStaff(new StaffEntity(idCreater(),"yusuf","okur"));
*/
/*        taskViewModel.getAllStaff().observe(this, new Observer<List<StaffEntity>>() {
            @Override
            public void onChanged(List<StaffEntity> staffEntities) {
*//*
                Toast.makeText(MainActivity.this, staffEntities.get(0)+"", Toast.LENGTH_SHORT).show();

*//*
                System.out.println(staffEntities.get(0).getName().toString());

            }
        });*/


/*
        getStaff();
*/
/*

        staffViewModel.insert(new StaffEntity(idCreater(),"yusuf","okur"));
        staffViewModel.insert(new StaffEntity(idCreater(),"yusuf1","okur1"));
        staffViewModel.insert(new StaffEntity(idCreater(),"yusuf2","okur2"));

*/


        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TaskEntity taskEntities) {
/*
                Toast.makeText(MainActivity.this, "id si"+taskEntities.getId(), Toast.LENGTH_SHORT).show();
*/


                Intent intent = new Intent(MainActivity.this, UpdateTask.class);
                intent.putExtra("id", taskEntities.getId());
                intent.putExtra("title", taskEntities.getTask());
                intent.putExtra("description", taskEntities.getTask());
                startActivityForResult(intent, 2);
                System.out.println("set on clik teki id " + taskEntities.getId());
            }
        });
        taskAdapter.setOnItemLongClickListener(new TaskAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(TaskEntity taskEntity) {
                Toast.makeText(getApplicationContext(), taskEntity.getDate()+"", Toast.LENGTH_SHORT).show();
                CheckBox checkBox = findViewById(R.id.checkBox);

            }
        });


    }

    // set menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.new_menu, menu);
        return true;
    }

    // set menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewTaskBtn:
                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.addNewStaffBtn:
                startActivity(new Intent(MainActivity.this, ManageStaff.class));
                return true;
            case R.id.settingsBtn:
/*
                startActivity(new Intent(MainActivity.this, ManageStaff.class));
*/
                return true;
                // delete btn get task adapter is selcted tak entity and remove

            case R.id.deleteBtn:

                for (TaskEntity taskEntity:taskAdapter.selectedItems){
                    taskViewModel.delete(taskEntity);
                    taskAdapter.isLongPressed = false;
                    taskAdapter.selectedItems.remove(taskEntity);
                }


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("taskTitle");
            String description = data.getStringExtra("taskDescription");

            TaskEntity taskEntity = new TaskEntity(idCreater(), title, "fdfgdfg",staffAdapter.selectedItems.toString());
            taskViewModel.insert(taskEntity);


            Toast.makeText(getApplicationContext(), "Task Saved", Toast.LENGTH_LONG).show();

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            String title = data.getStringExtra("titlelast");
            String description = data.getStringExtra("descriptionlast");
            long id = data.getLongExtra("taskid", -1);

            TaskEntity taskEntity = new TaskEntity(id, title, "sdfsfsdf",staffAdapter.selectedItems.toString());

            taskViewModel.update(taskEntity);
        }

    }


    private void init() {
        recyclerView = findViewById(R.id.recyclerview);

    }

    private void getAndSetItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(taskAdapter);

        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTask().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                //update Recyclerview
                taskAdapter.setTask(taskEntities);

            }
        });
    }

    private void itemGestures() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(taskAdapter.getTask(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }

    private void actionBar(String title) {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }



/*    private void getStaff(){

        staffViewModel = ViewModelProviders.of(this).get(StaffViewModel.class);
        staffViewModel.getAllStaff().observe(this, new Observer<List<StaffEntity>>() {
            @Override
            public void onChanged(List<StaffEntity> staffEntities) {
                //update Recyclerview
                System.out.println("staftayız");
                   System.out.println(staffViewModel.getAllStaff().toString());
                   LiveData<List<StaffEntity>> staffEntityList;


                System.out.println(staffEntities.get(0));





            }
        });
*//*
        staffViewModel.insert(new StaffEntity(idCreater(),"yusuf","okur"));
*//*

    }*/


}