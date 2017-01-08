package com.thyago.loaders;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<GitHubRepo>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 0x1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager()
                .initLoader(LOADER_ID, null, this)
                .forceLoad();
    }

    @Override
    public Loader<List<GitHubRepo>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader");
        return new GitHubReposAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<GitHubRepo>> loader, List<GitHubRepo> data) {
        Log.d(LOG_TAG, "onLoadFinished");
        for (GitHubRepo repo : data) {
            Log.d(LOG_TAG, repo.getName());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<GitHubRepo>> loader) {

    }
}
