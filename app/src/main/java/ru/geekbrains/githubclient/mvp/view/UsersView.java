package ru.geekbrains.githubclient.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;
import moxy.viewstate.strategy.alias.Skip;

@AddToEndSingle
public interface UsersView extends MvpView {
    void init();
    void updateList();
    @Skip void showError(String description);
}
