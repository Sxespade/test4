package ru.geekbrains.githubclient.mvp.model.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;
import ru.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;

public interface IDataSource {

    @GET("/users")
    Single<List<GithubUser>> getUsers();

    @GET
    Single<List<GithubRepository>> getRepos(@Url String reposUrl);
}
