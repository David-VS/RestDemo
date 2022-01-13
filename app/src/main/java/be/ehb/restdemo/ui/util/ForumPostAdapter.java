package be.ehb.restdemo.ui.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.ehb.restdemo.R;
import be.ehb.restdemo.model.ForumPost;

public class ForumPostAdapter extends RecyclerView.Adapter<ForumPostAdapter.ForumPostHolder> {

    class ForumPostHolder extends RecyclerView.ViewHolder{

        private final TextView titleTV, bodyTV;

        public ForumPostHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            bodyTV = itemView.findViewById(R.id.tv_body);
        }
    }

    ArrayList<ForumPost> data;

    public ForumPostAdapter(ArrayList<ForumPost> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ForumPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ForumPostHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumPostHolder holder, int position) {
        ForumPost forumPost = data.get(position);

        holder.titleTV.setText(forumPost.getTitle());
        holder.bodyTV.setText(forumPost.getBody());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
