package com.thyago.loaders;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.thyago.loaders.api.entity.GitHubRepo;
import com.thyago.loaders.api.GitHubReposAsyncTaskLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<GitHubRepo>> {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int LOADER_ID = 0x1010;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportLoaderManager()
                .initLoader(LOADER_ID, null, this)
                .forceLoad();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<List<GitHubRepo>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader");
        return new GitHubReposAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<GitHubRepo>> loader, List<GitHubRepo> data) {
        Log.d(LOG_TAG, "onLoadFinished");
        mRecyclerView.setAdapter(new GitHubReposAdapter(data));
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<GitHubRepo>> loader) {

    }
}
