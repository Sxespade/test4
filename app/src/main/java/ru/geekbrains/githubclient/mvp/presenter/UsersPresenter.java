package ru.geekbrains.githubclient.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter;
import ru.geekbrains.githubclient.mvp.view.UserItemView;
import ru.geekbrains.githubclient.mvp.view.UsersView;
import ru.geekbrains.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    Router router;
    @Inject
    IGithubUsersRepo userRepo;
    @Inject
    Scheduler scheduler;

    public UsersPresenter() {
        GithubApplication.getInstance().getAppComponent().inject(this);
    }

    public UsersPresenter(Scheduler scheduler, IGithubUsersRepo userRepo, Router router) {
        this.scheduler = scheduler;
        this.userRepo = userRepo;
        this.router = router;
    }

    public class UsersListPresenter implements IUserListPresenter {
        public final List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            router.navigateTo(
                new Screens.RepositoriesScreen(users.get(view.getPos())));
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
            view.setAvatar(user.getAvatarUrl());
        }

        @Override
        public int getCount() {
            return users.size();
        }
    }

    private final UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUsersListPresenter() {
        return usersListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }

    public void loadData() {
        disposables.add(userRepo.getUsers()
            .observeOn(scheduler)
            .subscribe(
                (userList) -> {
                    usersListPresenter.users.clear();
                    usersListPresenter.users.addAll(userList);
                    getViewState().updateList();
                },
                (throwable) -> getViewState().showError(throwable.getMessage())
            ));
    }

    public void onStop() {
        disposables.clear();
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
