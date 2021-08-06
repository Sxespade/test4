package com.geekbrains.githubclient.di.module;

import com.geekbrains.githubclient.GithubApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public class ApplicationModule {
    private final GithubApplication application;

    public ApplicationModule(GithubApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    GithubApplication getGithubApplication() {
        return application;
    }

    @Provides
    Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
