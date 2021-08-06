package ru.geekbrains.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;
import moxy.viewstate.strategy.alias.Skip;

@AddToEndSingle
public interface RepositoriesView extends MvpView {
    void init();
    void setLogin(String login);
    void setReposCount(int count);
    void updateList();
    @Skip void showError(String description);
}
