package ru.geekbrains.githubclient.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase;
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubUsersCache implements IGithubUsersCache {

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    GithubDatabase database;

    @Override
    public Completable saveUsers(List<GithubUser> users) {
        return Completable.fromAction(
              () -> database.githubUserDAO().insert(domainList2RoomList(users))
        );
    }

    @Override
    public Single<List<GithubUser>> loadUsers() {
        return Single.fromCallable(
              () -> roomList2DomainList(database.githubUserDAO().getAll()));
    }

    private List<RoomGithubUser> domainList2RoomList(List<GithubUser> users) {
        List<RoomGithubUser> roomUsers = new ArrayList<>();

        for (GithubUser user : users) {
            roomUsers.add(new RoomGithubUser(
                  user.getId(),
                  user.getLogin(),
                  user.getAvatarUrl(),
                  user.getReposUrl()));
        }

        return roomUsers;
    }

    private List<GithubUser> roomList2DomainList(List<RoomGithubUser> roomUsers) {
        List<GithubUser> users = new ArrayList<>();

        for (RoomGithubUser roomGithubUser : roomUsers) {
            users.add(new GithubUser(
                  roomGithubUser.getId(),
                  roomGithubUser.getLogin(),
                  roomGithubUser.getAvatarUrl(),
                  roomGithubUser.getReposUrl()));
        }

        return users;
    }
}
