package be.ehb.restdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import be.ehb.restdemo.R;
import be.ehb.restdemo.model.ForumPost;
import be.ehb.restdemo.model.ForumViewModel;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ForumViewModel forumViewModel = new ViewModelProvider(getActivity()).get(ForumViewModel.class);
        forumViewModel.getForumPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList<ForumPost>>() {
            @Override
            public void onChanged(ArrayList<ForumPost> forumPosts) {
                Log.d("TEST", "Data Loaded");
                Log.d("TEST", forumPosts.toString());
            }
        });
    }
}