package com.example.fitnesstracker;

import  androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.net.Uri;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ListItemHolder>{

    private WorkoutTrackerActivity workoutActivity;
    private ArrayList<Workout> list;

    public WorkoutAdapter (WorkoutTrackerActivity workoutActivity, ArrayList<Workout> list) {
        this.workoutActivity = workoutActivity;
        this.list = list;

    }
    @NonNull
    @Override
    public WorkoutAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.workout_list, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ListItemHolder holder, int position) {
        Workout workout = list.get (position);

        holder.textViewName.setText(workout.getName());
        holder.textViewType.setText(workout.getType());
        holder.textViewDur.setText(workout.getDuration() + " minutes");
        holder.textViewDelete.setText("Delete Workout");

        holder.textViewDelete.setOnClickListener(view -> {
            deleteItem(position);
        });

    }
    private void deleteItem(int position){
        if(position == RecyclerView.NO_POSITION){}
        else{
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewName;
        private TextView textViewType;

        private TextView textViewDur;
        private TextView textViewDelete;

        public ListItemHolder (View itemView) {
            super (itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDelete = itemView.findViewById(R.id.textViewDelete);
            textViewDur = itemView.findViewById(R.id.textViewDur);
            textViewName.setClickable(true);
            textViewName.setOnClickListener(this);

        }

        public void onClick (View view){
            int pos =  getAdapterPosition();


        }
    }

}
