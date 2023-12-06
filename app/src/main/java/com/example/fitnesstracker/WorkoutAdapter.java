package com.example.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ListItemHolder> {

    private ArrayList<Workout> workoutList;
    private OnWorkoutClickListener mListener;
    private WorkoutTracker workoutTracker;

    public WorkoutAdapter(WorkoutTracker workoutTracker, ArrayList<Workout> workoutList) {
        this.workoutList = workoutList;
        this.workoutTracker = workoutTracker;
    }

    public interface OnWorkoutClickListener {
        void onWorkoutClick(int position);
    }

    public void setOnWorkoutClickListener(OnWorkoutClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_list, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Workout workout = workoutList.get(position);

        holder.textViewName.setText(workout.getName());
        holder.textViewType.setText(workout.getType());
        holder.textViewDur.setText(String.valueOf(workout.getDuration()));
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    private void deleteItem(int position) {
        if (position != RecyclerView.NO_POSITION) {
            workoutList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

            // Update the workouts in SharedPreferences after deletion
            if (workoutTracker != null) {
                workoutTracker.saveWorkoutsToSharedPreferences(workoutList);
            }
        }
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewType;
        private TextView textViewDur;

        public ListItemHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDur = itemView.findViewById(R.id.textViewDur);
            textViewName.setOnClickListener(this);
            textViewType.setOnClickListener(this);
            textViewDur.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onWorkoutClick(getAdapterPosition());
            }
        }
    }
}
