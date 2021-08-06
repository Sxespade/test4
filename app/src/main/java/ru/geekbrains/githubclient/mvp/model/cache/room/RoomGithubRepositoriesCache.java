package ru.geekbrains.githubclient.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.cache.IGithubRepositoriesCache;
import ru.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.entity.room.GithubDatabase;
import ru.geekbrains.githubclient.mvp.model.entity.room.RoomGithubRepository;

public class RoomGithubRepositoriesCache implements IGithubRepositoriesCache {

    { GithubApplication.getInstance().getAppComponent().inject(this); }
    @Inject
    GithubDatabase database;

    @Override
    public Completable saveRepositories(List<GithubRepository> repositories, GithubUser user) {
        return Completable.fromAction(
              () -> database.githubRepositoryDAO().insert(domainList2RoomList(repositories, user))
        );
    }

    @Override
    public Single<List<GithubRepository>> loadRepositories(GithubUser user) {
        return Single.fromCallable(
              () -> roomList2DomainList(database.githubRepositoryDAO().getAll(user.getId()))
        );
    }

    private List<GithubRepository> roomList2DomainList(List<RoomGithubRepository> roomRepos) {
        List<GithubRepository> repos = new ArrayList<>();

        for (RoomGithubRepository roomRepo : roomRepos) {
            repos.add(new GithubRepository(
                  roomRepo.getId(), roomRepo.getName(),
                  roomRepo.getDescription(), roomRepo.getForks()
            ));
        }

        return repos;
    }

    private List<RoomGithubRepository> domainList2RoomList(
          List<GithubRepository> repos, GithubUser user) {
        List<RoomGithubRepository> roomRepos = new ArrayList<>();

        for (GithubRepository repo : repos) {
            roomRepos.add(new RoomGithubRepository(
                  repo.getId(), repo.getName(),
                  repo.getDescription(), repo.getForks(), user.getId()
            ));
        }

        return roomRepos;
    }
}
