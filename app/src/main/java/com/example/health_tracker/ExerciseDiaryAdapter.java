package com.example.health_tracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview#java

public class ExerciseDiaryAdapter extends RecyclerView.Adapter<ExerciseDiaryAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public TextView title;
        public TextView description;
        public TextView quantity;
        public TextView timestamp;



        public ExerciseViewHolder(View v) {
            super(v);
            mView = v;
            title = (TextView) v.findViewById(R.id.exercise_title_output);
            description = (TextView) v.findViewById(R.id.exercise_description_output);
            quantity = (TextView) v.findViewById(R.id.exercise_quantity_output);
            timestamp = (TextView) v.findViewById(R.id.exercise_timestamp_output);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ExerciseDiaryAdapter(List<Exercise> myDataset) {

        exercises = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ExerciseDiaryAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_exercise, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ExerciseViewHolder vh = new ExerciseViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(exercises.get(position).title);
        holder.description.setText(exercises.get(position).description);
        holder.quantity.setText(exercises.get(position).quantity);
        holder.timestamp.setText(exercises.get(position).timestamp);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
