package com.thyago.loaders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thyago.loaders.api.entity.GitHubRepo;

import java.util.List;

/**
 * Created by thyago on 08/01/2017.
 */

public class GitHubReposAdapter extends RecyclerView.Adapter<GitHubReposAdapter.ViewHolder> {

    private List<GitHubRepo> mRepos;

    public GitHubReposAdapter(List<GitHubRepo> mRepos) {
        super();
        this.mRepos = mRepos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.github_repo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mRepos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
