package com.resurrection.hizmettakip.util;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resurrection.hizmettakip.R;
import com.resurrection.hizmettakip.data.db.entity.StaffEntity;

import java.util.ArrayList;
import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffHolder> {

    private OnItemLongClickListener onItemLongClickListener;
    String staffId="";
    String staffName ="";
    String staffSurname = "";
    String staff;
    private List<StaffEntity> staffEntities = new ArrayList<>();
    private OnItemClickListener listener;
    public boolean isLongPressed = false;
    public ArrayList<String> selectedItems = new ArrayList<>();



    @NonNull
    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_item,parent,false);
        return new StaffHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
        StaffEntity currentStaffEntity = staffEntities.get(position);
        holder.name.setText(currentStaffEntity.getName().toString());
        holder.surname.setText(currentStaffEntity.getSurname().toString());
        if (isLongPressed) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return staffEntities.size();
    }
    public StaffEntity getStaff(int position){
        return staffEntities.get(position);
    }
    public void setStaff(List<StaffEntity> staffEntities){
        this.staffEntities = staffEntities;
        notifyDataSetChanged();
    }


    class StaffHolder extends RecyclerView.ViewHolder{
        private TextView name,surname;
        private CheckBox checkBox;

        public StaffHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            surname = itemView.findViewById(R.id.surname);
            checkBox = itemView.findViewById(R.id.staffCheckBox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    staffId += String.valueOf(staffEntities.get(position).getId());
                    staffName = String.valueOf(staffEntities.get(position).getName());
                    staffSurname = String.valueOf(staffEntities.get(position).getSurname());
                    staff = /*staffId+*/"*"+staffName+"*"+staffSurname+"|";

                    if (isLongPressed) {
                        if (!checkBox.isChecked()) {
                            checkBox.setChecked(true);


                            selectedItems.add(staff);
                            name.setTextColor(Color.parseColor("#ffffff"));

                        } else {
                            checkBox.setChecked(false);
                            selectedItems.remove(staff);
                            name.setTextColor(Color.parseColor("#0593c5"));


                        }
                    } else {
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(staffEntities.get(position));


                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    int position = getAdapterPosition();
                    staffId = String.valueOf(staffEntities.get(position).getId());
                    staffName = String.valueOf(staffEntities.get(position).getName());
                    staffSurname = String.valueOf(staffEntities.get(position).getSurname());
                    staff +=/* staffId+*/"*"+staffName+"*"+staffSurname+"|";

                    if (onItemLongClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemLongClickListener.onItemLongClick(staffEntities.get(position));
                        name.setTextColor(Color.parseColor("#ffffff"));
                        isLongPressed = true;
                        notifyDataSetChanged();
                        checkBox.setChecked(true);
                        selectedItems.add(staff);
                    }

                    return false;
                }
            });


        }
    }

       public interface OnItemClickListener {
        void onItemClick(StaffEntity staffEntity);
    }
        public interface OnItemLongClickListener {
        void onItemLongClick(StaffEntity staffEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

   public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;

    }
}
