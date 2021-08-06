package ru.geekbrains.githubclient.mvp.model.repo.retrofit;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;

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
