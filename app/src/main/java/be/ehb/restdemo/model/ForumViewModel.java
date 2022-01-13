package be.ehb.restdemo.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForumViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<ForumPost>> forumPosts;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    public ForumViewModel(@NonNull Application application) {
        super(application);
        forumPosts = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ForumPost>> getForumPosts() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();

                    Request mRequest = new Request.Builder()
                            .url("https://jsonplaceholder.typicode.com/posts")
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();

                    Log.d("TEST", mResponse.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return forumPosts;
    }
}
