package com.geekbrains.githubclient.mvp.model.repo;

import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;

public interface IGithubRepositoriesRepo {
    Single<List<GithubRepository>> getRepos(GithubUser user);
}
