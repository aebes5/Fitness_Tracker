package com.example.fitnesstracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ListItemHolder> {

    private ArrayList<FoodItem> foodItemList;
    private OnFoodClickListener mListener;
    private CalorieTracker calorieTracker;

    public FoodAdapter(ArrayList<FoodItem> foodItemList, OnFoodClickListener listener) {
        this.foodItemList = foodItemList;
        this.mListener = listener;
        this.calorieTracker = calorieTracker;
    }

    public interface OnFoodClickListener {
        void onFoodClick(int position);
    }

    public void setOnFoodClickListener(OnFoodClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);

        holder.textViewName.setText(foodItem.getFoodName());
        holder.textViewCalories.setText(String.valueOf(foodItem.getCalories())+ " cal.");
    }
//
    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    private void deleteItem(int position) {
        if (position != RecyclerView.NO_POSITION) {
            FoodItem deletedFoodItem = foodItemList.get(position);

            foodItemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

            // Update the food items in SharedPreferences after deletion
            if (calorieTracker != null) {
                calorieTracker.saveFoodItems(foodItemList);
            }
        }
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewCalories;


        public ListItemHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName); // Corrected reference
            textViewCalories = itemView.findViewById(R.id.textViewCalories); // Corrected reference
            textViewCalories.setOnClickListener(this);
            textViewName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onFoodClick(getAdapterPosition());
            }
        }
    }
}