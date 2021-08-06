package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import ru.geekbrains.githubclient.mvp.view.UsersView;
import ru.geekbrains.githubclient.ui.BackButtonListener;
import ru.geekbrains.githubclient.ui.adapter.UserRVAdapter;

public class UsersFragment extends MvpAppCompatFragment implements UsersView, BackButtonListener {
    public final static String LOG_TAG = "UsersFragment";

    private RecyclerView recyclerView;
    private View view;

    @InjectPresenter
    UsersPresenter usersPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.rv_users);

        return view;
    }

    @Override
    public void init() {
        UserRVAdapter adapter = new UserRVAdapter(usersPresenter.getUsersListPresenter());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        usersPresenter.onStop();
    }

    @Override
    public void updateList() {
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showError(String description) {
        Log.w(LOG_TAG, description);
        Toast.makeText(getActivity(), description, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean backPressed() {
        return usersPresenter.backPressed();
    }
}
