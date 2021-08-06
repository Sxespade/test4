package com.geekbrains.githubclient.di.module;

import androidx.room.Room;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.geekbrains.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;

@Module
public class CacheModule {
    @Singleton
    @Provides
    GithubDatabase getGithubDatabase(GithubApplication application) {
        return Room.databaseBuilder(application, GithubDatabase.class,
              GithubDatabase.GITHUB_DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    IGithubUsersCache getIGithubUsersCache() {
        return new RoomGithubUsersCache();
    }

    @Singleton
    @Provides
    IGithubRepositoriesCache getIGithubRepositoriesCache() {
        return new RoomGithubRepositoriesCache();
    }
}
