package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.model.entity.GithubRepository;
import ru.geekbrains.githubclient.mvp.presenter.ShowRepositoryPresenter;
import ru.geekbrains.githubclient.mvp.view.ShowRepositoryView;
import ru.geekbrains.githubclient.ui.BackButtonListener;

public class ShowRepositoryFragment extends MvpAppCompatFragment
    implements ShowRepositoryView, BackButtonListener {

    private final static String BUNDLE_KEY_USER_REPOSITORY = "ShowRepositoryFragment.userRepository";

    private TextView repositoryName;
    private TextView repositoryDescription;
    private TextView repositoryForkCount;

    @InjectPresenter
    ShowRepositoryPresenter showRepositoryPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            GithubRepository githubRepository = bundle.getParcelable(BUNDLE_KEY_USER_REPOSITORY);
            showRepositoryPresenter.configure(githubRepository);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_repository, container, false);

        repositoryName = view.findViewById(R.id.tv_repo_name);
        repositoryDescription = view.findViewById(R.id.tv_repo_description);
        repositoryForkCount = view.findViewById(R.id.tv_repo_fork_count);

        return view;
    }

    public static Fragment getInstance(GithubRepository githubRepository) {
        ShowRepositoryFragment fragment = new ShowRepositoryFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_KEY_USER_REPOSITORY, githubRepository);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void setForkCount(int forks) {
        String string = forks + " "
            + (forks == 1 ?
            getString(R.string.caption_fork)
            : getString(R.string.caption_forks));
        repositoryForkCount.setText(string);
    }

    @Override
    public void setRepositoryName(String name) {
        repositoryName.setText(name);
    }

    @Override
    public void setRepositoryDescription(String description) {
        repositoryDescription.setText(description);
    }

    @Override
    public boolean backPressed() {
        return showRepositoryPresenter.backPressed();
    }
}
