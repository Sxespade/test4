package ru.geekbrains.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface ShowRepositoryView extends MvpView {
    void setForkCount(int forks);
    void setRepositoryName(String name);
    void setRepositoryDescription(String description);
}
