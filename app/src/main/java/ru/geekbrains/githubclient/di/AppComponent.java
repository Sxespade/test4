package ru.geekbrains.githubclient.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.githubclient.MainActivity;
import ru.geekbrains.githubclient.di.module.ApiModule;
import ru.geekbrains.githubclient.di.module.ApplicationModule;
import ru.geekbrains.githubclient.di.module.CacheModule;
import ru.geekbrains.githubclient.di.module.CiceroneModule;
import ru.geekbrains.githubclient.di.module.RepoModule;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.presenter.MainPresenter;
import ru.geekbrains.githubclient.mvp.presenter.RepositoriesPresenter;
import ru.geekbrains.githubclient.mvp.presenter.ShowRepositoryPresenter;
import ru.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import ru.geekbrains.githubclient.ui.network.AndroidNetworkStatus;

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
