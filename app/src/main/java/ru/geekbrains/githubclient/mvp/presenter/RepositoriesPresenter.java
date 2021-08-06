package ru.geekbrains.githubclient.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import ru.geekbrains.githubclient.mvp.presenter.list.IRepositoriesListPresenter;
import ru.geekbrains.githubclient.mvp.view.RepositoriesView;
import ru.geekbrains.githubclient.mvp.view.RepositoryItemView;
import ru.geekbrains.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class RepositoriesPresenter extends MvpPresenter<RepositoriesView> {
    private GithubUser githubUser;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    Scheduler scheduler;
    @Inject
    IGithubRepositoriesRepo repositoriesRepo;
    @Inject
    Router router;

    public RepositoriesPresenter() {
        GithubApplication.getInstance().getAppComponent().inject(this);
    }

    public RepositoriesPresenter(
          Scheduler scheduler,
          IGithubRepositoriesRepo repositoriesRepo,
          Router router) {
        this.scheduler = scheduler;
        this.repositoriesRepo = repositoriesRepo;
        this.router = router;
    }

    private class RepositoriesListPresenter implements IRepositoriesListPresenter {
        private final List<GithubRepository> repositories = new ArrayList<>();

        @Override
        public void onItemClick(RepositoryItemView view) {
            router.navigateTo(
                  new Screens.ShowRepositoryScreen(repositories.get(view.getPos())));
        }

        @Override
        public void bindView(RepositoryItemView view) {
            GithubRepository repository = repositories.get(view.getPos());
            view.setName(repository.getName());
            view.setDescription(repository.getName());
        }

        @Override
        public int getCount() {
            return repositories.size();
        }
    }

    private final RepositoriesListPresenter repositoriesListPresenter = new RepositoriesListPresenter();

    public RepositoriesListPresenter getRepositoriesListPresenter() {
        return repositoriesListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        getViewState().setLogin(githubUser.getLogin());
        loadData();
    }

    private void loadData() {
        disposables.add(repositoriesRepo
              .getRepos(githubUser)
              .observeOn(scheduler)
              .subscribe(
                    (userRepositories) -> {
                        repositoriesListPresenter.repositories.clear();
                        repositoriesListPresenter.repositories.addAll(userRepositories);
                        getViewState().setReposCount(userRepositories.size());
                        getViewState().updateList();
                    }
              ));
    }

    public void configure(GithubUser githubUser) {
        this.githubUser = githubUser;
    }

    public void onStop() {
        disposables.dispose();
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
