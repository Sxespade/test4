package ru.geekbrains.githubclient.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

@Module
public class RepoModule {

    @Singleton
    @Provides
    IGithubRepositoriesRepo getIGithubRepositoriesRepo() {
        return new RetrofitGithubRepositoriesRepo();
    }

    @Singleton
    @Provides
    IGithubUsersRepo getIGithubUsersRepo() {
        return new RetrofitGithubUsersRepo();
    }


}
