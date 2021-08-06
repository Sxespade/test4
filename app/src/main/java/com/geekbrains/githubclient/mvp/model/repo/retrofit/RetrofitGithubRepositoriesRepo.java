package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    IDataSource dataSource;
    @Inject
    INetworkStatus networkStatus;
    @Inject
    IGithubRepositoriesCache repositoriesCache;

    @Override
    public Single<List<GithubRepository>> getRepos(GithubUser user) {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
                  if (isOnline) {
                      String url = user.getReposUrl();

                      if (url == null) {

                          return Single.fromCallable(Collections::<GithubRepository>emptyList);
                      } else {
                          return dataSource.getRepos(user.getReposUrl()).doOnSuccess(
                                repositories -> repositoriesCache.saveRepositories(repositories, user));
                      }
                  } else {
                      return repositoriesCache.loadRepositories(user);
                  }
              }
        ).subscribeOn(Schedulers.io());
    }
}
