package com.geekbrains.githubclient.di.module;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.ui.network.AndroidNetworkStatus;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Named ("baseUrl")
    @Provides
    String baseUrl() {
        return "https://api.github.com/";
    }

    @Singleton
    @Provides
    IDataSource getIDataSource(@Named ("baseUrl") String baseUrl) {
        Gson gson = new GsonBuilder()
              .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
              .excludeFieldsWithoutExposeAnnotation()
              .create();

        Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create(gson))
              .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
              .build();

        return retrofit.create(IDataSource.class);
    }

    @Singleton
    @Provides
    INetworkStatus getINetworkStatus() {
        return new AndroidNetworkStatus();
    }
}
