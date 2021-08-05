package com.resurrection.hizmettakip.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.resurrection.hizmettakip.R;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.ui.base.TaskViewModel;
import com.resurrection.hizmettakip.util.StaffAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ManageStaff extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaffAdapter staffAdapter = new StaffAdapter();
    private TaskViewModel taskViewModel;
    private Button addStaff;
    private TextView name,surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff);
        init();
        getAndSetItems();
        actionBar("Personeller");
        itemGestures();

        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") && !surname.getText().toString().equals("")){
                    taskViewModel.insertStaff(new StaffEntity(idCreater(),name.getText().toString(),surname.getText().toString()));
                    name.setText("");
                    surname.setText("");
                }else {
                    Toast.makeText(ManageStaff.this, "lütfen eksik yerleri doldurunuz", Toast.LENGTH_SHORT).show();
                }

            }
        });


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

    void init() {
        recyclerView = findViewById(R.id.staffRecyclerview);
        addStaff= findViewById(R.id.addStaff);
        name= findViewById(R.id.staffEditName);
        surname= findViewById(R.id.staffEditSurname);
    }

    private void actionBar(String title) {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }
    private void itemGestures(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.deleteStaff(staffAdapter.getStaff(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    private long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }







}




























