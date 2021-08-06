package com.geekbrains.githubclient.di.module;

import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

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
