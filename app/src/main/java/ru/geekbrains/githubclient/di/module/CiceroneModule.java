package ru.geekbrains.githubclient.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class CiceroneModule {
    private final Cicerone<Router> cicerone = Cicerone.create();

    @Provides
    Cicerone<Router> getCicerone() {
        return cicerone;
    }

    @Singleton
    @Provides
    Router getRouter() {
        return cicerone.getRouter();
    }

    @Singleton
    @Provides
    NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
