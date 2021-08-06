package com.geekbrains.githubclient.di;

import javax.inject.Singleton;

import dagger.Component;
import com.geekbrains.githubclient.MainActivity;
import com.geekbrains.githubclient.di.module.ApiModule;
import com.geekbrains.githubclient.di.module.ApplicationModule;
import com.geekbrains.githubclient.di.module.CacheModule;
import com.geekbrains.githubclient.di.module.CiceroneModule;
import com.geekbrains.githubclient.di.module.RepoModule;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;
import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import com.geekbrains.githubclient.mvp.presenter.MainPresenter;
import com.geekbrains.githubclient.mvp.presenter.RepositoriesPresenter;
import com.geekbrains.githubclient.mvp.presenter.ShowRepositoryPresenter;
import com.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import com.geekbrains.githubclient.ui.network.AndroidNetworkStatus;

@Singleton
@Component( modules = {
      ApiModule.class,
      ApplicationModule.class,
      CacheModule.class,
      CiceroneModule.class,
      RepoModule.class
})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);
    void inject(RepositoriesPresenter repositoriesPresenter);
    void inject(UsersPresenter usersPresenter);
    void inject(ShowRepositoryPresenter showRepositoryPresenter);

    void inject(RetrofitGithubUsersRepo retrofitGithubUsersRepo);
    void inject(RetrofitGithubRepositoriesRepo retrofitGithubRepositoriesRepo);

    void inject(RoomGithubUsersCache roomGithubUsersCache);
    void inject(RoomGithubRepositoriesCache roomGithubRepositoriesCache);

    void inject(AndroidNetworkStatus androidNetworkStatus);
}
