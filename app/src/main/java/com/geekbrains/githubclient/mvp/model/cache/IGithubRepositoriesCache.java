package com.geekbrains.githubclient.mvp.model.cache;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IGithubRepositoriesCache {
    Completable saveRepositories(List<GithubRepository> repositories, GithubUser user);
    Single<List<GithubRepository>> loadRepositories(GithubUser user);
}
