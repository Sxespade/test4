package com.geekbrains.githubclient;

import android.app.Application;

import com.geekbrains.githubclient.di.AppComponent;
import com.geekbrains.githubclient.di.DaggerAppComponent;
import com.geekbrains.githubclient.di.module.ApplicationModule;

public class GithubApplication extends Application {
    static GithubApplication instance;
    static AppComponent appComponent;

    public static final boolean DEBUG_MODE = true;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appComponent = DaggerAppComponent.builder()
              .applicationModule(new ApplicationModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static GithubApplication getInstance() {
        return instance;
    }
}
