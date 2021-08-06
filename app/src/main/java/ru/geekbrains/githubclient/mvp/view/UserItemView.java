package ru.geekbrains.githubclient.mvp.view;

public interface UserItemView extends IItemView {
    void setLogin(String text);
    void setAvatar(String url);
}
