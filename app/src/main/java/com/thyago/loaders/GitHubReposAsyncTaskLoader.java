package com.thyago.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thyago on 08/01/2017.
 */

public class GitHubReposAsyncTaskLoader extends AsyncTaskLoader<List<GitHubRepo>> {

    private static final String LOG_TAG = GitHubReposAsyncTaskLoader.class.getSimpleName();

    public GitHubReposAsyncTaskLoader(Context context) {
        super(context);
    }

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
    public List<GitHubRepo> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground");
        try {
            Thread.sleep(5000);
            GitHubRepos repos = createRetrofitService(GitHubRepos.class, GitHubRepos.SERVICE_ENDPOINT);
            return repos.userRepos("thyagostall").execute().body();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }
    }
}
