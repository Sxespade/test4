package ru.geekbrains.githubclient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import ru.terrakok.cicerone.Router;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersPresenterTest {

    private UsersPresenter presenter;

    private final Scheduler scheduler = Schedulers.trampoline();

    @Mock
    IGithubUsersRepo userRepo;

    @Mock
    Router router;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = new UsersPresenter(scheduler, userRepo, router);
    }

    @Test
    public void navigateBack_Test() {
        presenter.backPressed();
        verify(router, Mockito.times(1)).exit();
    }

    @Test
    public void loadData_Test() {
        when(userRepo.getUsers())
              .thenReturn(
                    Single.just(new ArrayList<>())
              );

        presenter.loadData();

        assertNotNull(presenter.getUsersListPresenter().users);
        assertEquals(new ArrayList<GithubUser>(), presenter.getUsersListPresenter().users);
        assertEquals(0, presenter.getUsersListPresenter().getCount());
    }
}