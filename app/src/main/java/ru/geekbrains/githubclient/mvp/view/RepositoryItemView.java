package ru.geekbrains.githubclient.mvp.view;

public interface RepositoryItemView extends IItemView {
    void setName(String name);
    void setDescription(String description);
}
