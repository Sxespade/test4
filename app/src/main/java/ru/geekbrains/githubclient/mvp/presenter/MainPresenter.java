package ru.geekbrains.githubclient.mvp.presenter;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.view.MainView;
import ru.geekbrains.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    Router router;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked() {
        router.exit();
    }
}
