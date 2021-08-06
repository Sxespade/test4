package ru.geekbrains.githubclient.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;

public interface IGithubRepositoriesCache {
    Completable saveRepositories(List<GithubRepository> repositories, GithubUser user);
    Single<List<GithubRepository>> loadRepositories(GithubUser user);
}
