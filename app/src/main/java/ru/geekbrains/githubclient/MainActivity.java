package ru.geekbrains.githubclient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.geekbrains.githubclient.mvp.presenter.MainPresenter;
import ru.geekbrains.githubclient.mvp.view.MainView;
import ru.geekbrains.githubclient.ui.BackButtonListener;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    NavigatorHolder navigatorHolder;

    private final Navigator navigator = new SupportAppNavigator(
          this, getSupportFragmentManager(), R.id.container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backPressed()) {
                return;
            }
        }

        presenter.backClicked();
    }
}