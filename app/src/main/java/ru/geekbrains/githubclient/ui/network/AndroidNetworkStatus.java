package ru.geekbrains.githubclient.ui.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.network.INetworkStatus;

public class AndroidNetworkStatus implements INetworkStatus {
    private final static String LOG_TAG = AndroidNetworkStatus.class.getName();

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    GithubApplication application;

    private final BehaviorSubject<Boolean> statusBus = BehaviorSubject.create();

    public AndroidNetworkStatus() {
        statusBus.onNext(false);

        ConnectivityManager connectivityManager =
              (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkRequest networkRequest = new NetworkRequest.Builder().build();

        connectivityManager
            .registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);

                    if (GithubApplication.DEBUG_MODE) {
                        Log.w(LOG_TAG, "onAvailable");
                    }

                    statusBus.onNext(true);
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();

                    if (GithubApplication.DEBUG_MODE) {
                        Log.w(LOG_TAG, "onUnavailable");
                    }

                    statusBus.onNext(false);
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);

                    if (GithubApplication.DEBUG_MODE) {
                        Log.w(LOG_TAG, "onLost");
                    }

                    statusBus.onNext(false);
                }
            });
    }

    @Override
    public Observable<Boolean> isOnline() {
        return statusBus;
    }

    @Override
    public Single<Boolean> isOnlineSingle() {
        return statusBus.first(false);
    }
}
