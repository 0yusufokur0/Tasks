package com.resurrection.hizmettakip.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.hizmettakip.MainActivity;
import com.resurrection.hizmettakip.R;
import com.resurrection.hizmettakip.data.db.TaskDatabase;
import com.resurrection.hizmettakip.data.db.dao.TaskDao;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;
import com.resurrection.hizmettakip.data.db.entity.TaskEntity;
import com.resurrection.hizmettakip.ui.home.AddTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<TaskEntity> taskEntities = new ArrayList<>();
    private List<StaffEntity> staffEntities = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener onItemLongClickListener;
    public boolean isLongPressed = false;
    public ArrayList<TaskEntity> selectedItems = new ArrayList<TaskEntity>();
    private TaskDao taskDao;
    Context context;
    private String tempStaff = "";
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        context = parent.getContext();
        context = view.getContext();


        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        TaskEntity currentTask = taskEntities.get(position);


/*        String temp =  " ";
        temp = temp  + staffEntities.get(0).getName().toString();*/

        holder.textViewTitle.setText(currentTask.getTask());


        TaskDatabase taskDatabase = TaskDatabase.getInstance(context);
        taskDao = taskDatabase.taskDao();



        holder.textViewDescription.setText(currentTask.getStaff());

        if (isLongPressed) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return taskEntities.size();
    }

    public void setTask(List<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
        notifyDataSetChanged();
    }

    public TaskEntity getTask(int position) {
        return taskEntities.get(position);
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private CheckBox checkBox;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            checkBox = itemView.findViewById(R.id.checkBox);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (isLongPressed) {
                        if (!checkBox.isChecked()) {
                            checkBox.setChecked(true);
                            selectedItems.add(taskEntities.get(position));
                            textViewTitle.setTextColor(Color.parseColor("#ffffff"));

                        } else {
                            checkBox.setChecked(false);
                            selectedItems.remove(taskEntities.get(position));
                            textViewTitle.setTextColor(Color.parseColor("#0593c5"));


                        }
                    } else {
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(taskEntities.get(position));


                        }
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if (onItemLongClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemLongClickListener.onItemLongClick(taskEntities.get(position));
                        textViewTitle.setTextColor(Color.parseColor("#ffffff"));
                        isLongPressed = true;
                        notifyDataSetChanged();
                        checkBox.setChecked(true);
                        selectedItems.add(taskEntities.get(position));


                    }

                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TaskEntity taskEntities);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(TaskEntity taskEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;

    }


}
