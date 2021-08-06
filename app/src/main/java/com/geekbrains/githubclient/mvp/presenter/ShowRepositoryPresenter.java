package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.view.ShowRepositoryView;

import javax.inject.Inject;

import moxy.MvpPresenter;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;

import ru.terrakok.cicerone.Router;

public class ShowRepositoryPresenter extends MvpPresenter<ShowRepositoryView> {
    private GithubRepository githubRepository;

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    Router router;

    public void configure(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().setRepositoryName(githubRepository.getName());
        getViewState().setRepositoryDescription(githubRepository.getDescription());
        getViewState().setForkCount(githubRepository.getForks());
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
