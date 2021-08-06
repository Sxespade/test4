package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubUsersRepo implements IGithubUsersRepo {

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    IDataSource dataSource;
    @Inject
    INetworkStatus networkStatus;
    @Inject
    IGithubUsersCache userCache;

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap(isOnline -> {
            if (isOnline) {
                return dataSource.getUsers().doOnSuccess(userCache::saveUsers);
            } else {
                return userCache.loadUsers();
            }
        }).subscribeOn(Schedulers.io());
    }
}
