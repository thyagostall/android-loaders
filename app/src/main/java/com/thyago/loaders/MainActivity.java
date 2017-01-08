package com.thyago.loaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<GitHubRepo>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static <T> T createRetrofitService(final Class<T> c, final String endPoint) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        GsonConverterFactory gsonFactory = GsonConverterFactory.create(gson);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(gsonFactory)
                .baseUrl(endPoint)
                .build();

        return retrofit.create(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubRepos repos = createRetrofitService(GitHubRepos.class, GitHubRepos.SERVICE_ENDPOINT);
        repos.userRepos("thyagostall").enqueue(this);
    }

    @Override
    public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
        for (GitHubRepo repo : response.body()) {
            Log.d(LOG_TAG, repo.getName());
        }
    }

    @Override
    public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
        Log.d(LOG_TAG, "Ops! Something went wrong!");
    }
}
