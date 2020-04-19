package com.example.lab4ost;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab4ost.TaskFragment.OnListFragmentInteractionListener;
import com.example.lab4ost.tasks.TaskListContent.Task;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = mValues.get(position);
        holder.mItem = task;
        holder.mContentView.setText(task.name);
        final String picPath = task.number;
        Context context = holder.mView.getContext();
        if(picPath != null && !picPath.isEmpty()){
          if(picPath.contains("drawable")) {
              Drawable taskDrawable;
              switch (picPath) {
                  case "drawable 1":
                      taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                      break;
                  case "drawable 2":
                      taskDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                      break;
                  case "drawable 3":
                      taskDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                      break;
                  default:
                      taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
              }
              holder.mItemImageView.setImageDrawable(taskDrawable);
          }
        } else{
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_1));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v){
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }

        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public final ImageButton mdeleteButton;
        public Task mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = (ImageView) view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            mdeleteButton = view.findViewById(R.id.deleteButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
