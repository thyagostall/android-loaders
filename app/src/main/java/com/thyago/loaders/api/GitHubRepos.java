package com.thyago.loaders.api;

import com.thyago.loaders.api.entity.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thyago on 08/01/2017.
 */

public interface GitHubRepos {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> userRepos(@Path("user") String user);
}
