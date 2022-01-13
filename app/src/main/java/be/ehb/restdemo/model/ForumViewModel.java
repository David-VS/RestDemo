package be.ehb.restdemo.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
       ArrayList<ForumPost> posts = new ArrayList<>();

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

                    String responsePlainText = mResponse.body().string();
                    JSONArray postArray = new JSONArray(responsePlainText);

                    int nObjects = postArray.length();
                    int i = 0;

                    while(i < nObjects){
                        JSONObject currentPostJSON = postArray.getJSONObject(i);

                        ForumPost currentPostJava = new ForumPost(
                                currentPostJSON.getInt("userId"),
                                currentPostJSON.getInt("id"),
                                currentPostJSON.getString("title"),
                                currentPostJSON.getString("body")
                        );
                        posts.add(currentPostJava);
                        i++;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                forumPosts.postValue(posts);
            }
        });

        return forumPosts;
    }
}
