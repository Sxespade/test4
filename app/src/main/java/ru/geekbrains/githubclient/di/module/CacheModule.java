package ru.geekbrains.githubclient.di.module;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache;
import ru.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase;

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
