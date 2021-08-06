package com.geekbrains.githubclient.mvp.model.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

public interface IDataSource {

    @GET("/users")
    Single<List<GithubUser>> getUsers();

    @GET
    Single<List<GithubRepository>> getRepos(@Url String reposUrl);
}
