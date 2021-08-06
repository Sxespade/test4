package com.geekbrains.githubclient.navigation;

import androidx.fragment.app.Fragment;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.ui.fragments.RepositoriesFragment;
import com.geekbrains.githubclient.ui.fragments.ShowRepositoryFragment;
import com.geekbrains.githubclient.ui.fragments.UsersFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UsersScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new UsersFragment();
        }
    }

    public static class RepositoriesScreen extends SupportAppScreen {
        private final GithubUser githubUser;

        public RepositoriesScreen(GithubUser githubUser) {
            super();
            this.githubUser = githubUser;
        }

        @Override
        public Fragment getFragment() {
            return RepositoriesFragment.getInstance(githubUser);
        }
    }

    public static class ShowRepositoryScreen extends SupportAppScreen {
        private final GithubRepository githubRepository;

        public ShowRepositoryScreen(GithubRepository githubRepository) {
            super();
            this.githubRepository = githubRepository;
        }

        @Override
        public Fragment getFragment() {
            return ShowRepositoryFragment.getInstance(githubRepository);
        }
    }
}
